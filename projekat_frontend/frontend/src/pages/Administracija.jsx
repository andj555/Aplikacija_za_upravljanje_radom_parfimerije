import React, { useState, useEffect } from "react";
import { proizvodApi, poreskaStopaApi, porudzbinaApi, otpremnicaApi, korisnikApi, kategorijaMirisaApi } from "../api";

function Administracija() {

  const trenutniKorisnik = JSON.parse(localStorage.getItem("korisnik") || "{}");
  const jeZaposleni = trenutniKorisnik.tipKorisnika === "ZAPOSLENI";

  const [trenutnaStranica, setTrenutnaStranica] = useState("proizvodi");
  const [listaProizvoda, setListaProizvoda] = useState([]);
  const [listaPoreskihStopa, setListaPoreskihStopa] = useState([]);
  const [listaKategorijaMirisa, setListaKategorijaMirisa] = useState([]);
  const [listaPorudzbina, setListaPorudzbina] = useState([]);
  const [listaOtpremnica, setListaOtpremnica] = useState([]);
  const [listaKorisnika, setListaKorisnika] = useState([]);
  const [selektovanaPorudzbina, setSelektovanaPorudzbina] = useState(null);
  const [izmeniPorudzbine, setIzmeniPorudzbine] = useState(null);
  const [izmenaPopusta, setIzmenaPopusta] = useState('');
  
  const [formProizvod, setFormProizvod] = useState({ 
    naziv: "", 
    opis: "", 
    slika: "", 
    cena: "", 
    kolicinaNaStanju: "", 
    marka: "", 
    kategorijaPol: "", 
    idKategorijaMirisa: "", 
    idPoreskaStopa: "" 
  });
  
  const [izmenaProizvoda, setIzmenaProizvoda] = useState(null);
  const [formPoreskaStopa, setFormPoreskaStopa] = useState({ naziv: "", procenat: "" });
  const [stavkeOtpremniceMap, setStavkeOtpremniceMap] = useState({});
  const [statusFilter, setStatusFilter] = useState("");
  const [kupacFilter, setKupacFilter] = useState("");
  const [otpremnicaFilter, setOtpremnicaFilter] = useState("");

  useEffect(() => {
    if (!jeZaposleni) {
      alert("Nemate pristup ovoj stranici");
      window.location.href = "/";
      return;
    }
    ucitajPodatke();
  }, []);

  const ucitajPodatke = async () => {
    try {
      const [proizvodiRes, korisniciRes, stopeRes, mirisiRes, porudzbineRes, otpremniceRes] = await Promise.all([
        proizvodApi.getAll(),
        korisnikApi.getAll(),
        poreskaStopaApi.getAll(),
        kategorijaMirisaApi?.getAll ? kategorijaMirisaApi.getAll() : Promise.resolve({ data: [] }),
        porudzbinaApi.getAll(),
        otpremnicaApi.getAll()
      ]);

      setListaProizvoda(proizvodiRes.data || []);
      setListaKorisnika(korisniciRes.data || []);
      setListaPoreskihStopa(stopeRes.data || []);
      setListaKategorijaMirisa(mirisiRes.data || []);
      setListaPorudzbina(porudzbineRes.data || []);
      setListaOtpremnica(otpremniceRes.data || []);
    } catch (error) {
      console.error("Greska: ", error);
    }
  };

  const sacuvajProizvod = async () => {
    if (!formProizvod.naziv) {
      alert("Mora postojati naziv");
      return;
    }
    try {
      await proizvodApi.save({
        ...formProizvod,
        cena: parseFloat(formProizvod.cena) || 0,
        kolicinaNaStanju: parseInt(formProizvod.kolicinaNaStanju) || 0,
        idPoreskaStopa: formProizvod.idPoreskaStopa ? parseInt(formProizvod.idPoreskaStopa) : null,
        idKategorijaMirisa: formProizvod.idKategorijaMirisa ? parseInt(formProizvod.idKategorijaMirisa) : null,
      });
      alert("Proizvod je sačuvan");
      setFormProizvod({ naziv: "", opis: "", slika: "", cena: "", kolicinaNaStanju: "", marka: "", kategorijaPol: "", idKategorijaMirisa: "", idPoreskaStopa: "" });
      ucitajPodatke();
    } catch (error) {
      alert("Greška pri čuvanju proizvoda");
    }
  };

  const izmeniProizvod = async () => {
    try {
      await proizvodApi.update(izmenaProizvoda.idProizvod, {
        ...izmenaProizvoda,
        cena: parseFloat(izmenaProizvoda.cena) || 0,
        kolicinaNaStanju: parseInt(izmenaProizvoda.kolicinaNaStanju) || 0,
        idPoreskaStopa: izmenaProizvoda.idPoreskaStopa ? parseInt(izmenaProizvoda.idPoreskaStopa) : null,
        idKategorijaMirisa: izmenaProizvoda.idKategorijaMirisa ? parseInt(izmenaProizvoda.idKategorijaMirisa) : null,
      });
      alert("Izmene su sačuvane");
      setIzmenaProizvoda(null);
      ucitajPodatke();
    } catch (error) {
      alert("Greška pri izmeni proizvoda");
    }
  };

  const obrisiProizvod = async (id) => {
    if (window.confirm("Da li želite da obrišete proizvod?")) {
      try {
        await proizvodApi.delete(id);
        ucitajPodatke();
      } catch (error) {
        alert("Proizvod se ne može obrisati");
      }
    }
  };

  const sacuvajPoreskuStopu = async () => {
    if (formPoreskaStopa.procenat === "") {
      alert("Unesite procenat poreske stope");
      return;
    }
    try {
      await poreskaStopaApi.save({
        naziv: formPoreskaStopa.naziv,
        procenat: parseFloat(formPoreskaStopa.procenat)
      });
      alert("Poreska stopa dodata!");
      setFormPoreskaStopa({ naziv: "", procenat: "" });
      ucitajPodatke();
    } catch (error) {
      alert("Greška");
    }
  };

  const getKupac = (p) => {
    if (p.ime || p.prezime) {
      return `${p.ime || ""} ${p.prezime || ""}`.trim();
    }
    const idKorisnika = p.idKorisnik;
    const email = p.email;
    const kupac = listaKorisnika.find(
      (k) => (idKorisnika && k.idKorisnik === idKorisnika) || (email && k.email === email)
    );

    if (kupac) {
      const ime = kupac.ime || "";
      const prezime = kupac.prezime || "";
      if (ime || prezime) {
        return `${ime} ${prezime}`.trim();
      }
      return kupac.email || `-`;
    }

    return email || `-`;
  };

  const otkaziPorudzbinu = async (id) => {
    if (window.confirm("Da li ste sigurni da želite da otkažete porudžbinu?")) {
      try {
        await porudzbinaApi.otkazi(id);
        ucitajPodatke();
      } catch (error) {
        alert("Greška pri otkazivanju porudžbine");
      }
    }
  };

  const sacuvajPopustPorudzbine = async (idPorudzbina) => {
    try {
      if (porudzbinaApi.updatePopust) {
        await porudzbinaApi.updatePopust(idPorudzbina, izmenaPopusta);
      } else {
        await porudzbinaApi.update(idPorudzbina, { popust: parseFloat(izmenaPopusta) || 0 });
      }
      
      setIzmeniPorudzbine(null);
      setIzmenaPopusta('');
      ucitajPodatke();
    } catch (error) {
      alert("Greška pri čuvanju popusta");
    }
  };

  const otkaziOtpremnicu = async (id) => {
   console.log("Pokušaj otkazivanja za ID:", id);
  if (window.confirm("Da li želite otkazati otpremnicu?")) {
    try {
      const res = await otpremnicaApi.otkazi(id);
      console.log("Odgovor backenda:", res);
      ucitajPodatke();
    } catch (error) {
      alert("Greška: " + (error.response?.data?.message || error.message));
    }
  }
  };

  const izracunajIsporuceno = (idPorudzbina, idProizvod) => {
    return listaOtpremnica
      .filter((o) => o.idPorudzbina === idPorudzbina && o.status === "IZDATA")
      .flatMap((o) => o.stavke || [])
      .filter((s) => s.idProizvod === idProizvod)
      .reduce((zbir, s) => zbir + s.kolicina, 0);
  };

  const generisiOtpremnicu = async (idPorudzbina, stavke) => {
    const stavkeZaSlanje = stavke
      .map((s) => ({
        idProizvod: s.idProizvod,
        kolicina: parseInt(stavkeOtpremniceMap[s.idProizvod]) || 0,
      }))
      .filter((s) => s.kolicina > 0);

    if (stavkeZaSlanje.length === 0) {
      alert("Unesite količinu");
      return;
    }

    try {
      const res = await otpremnicaApi.generisi({ idPorudzbina, stavke: stavkeZaSlanje });
      alert("Otpremnica je kreirana. Broj: " + res.data.brojOtpremnice);
      setStavkeOtpremniceMap({});
      ucitajPodatke();
    } catch (error) {
      alert("Greška pri kreiranju otpremnice.");
    }
  };

  const filtriranePorudzbine = listaPorudzbina.filter((p) => {
    const ispravanStatus = !statusFilter || p.statusPorudzbine === statusFilter;
    if (!kupacFilter) return ispravanStatus;

    const pretraga = kupacFilter.toLowerCase().trim();
    const imePrezimeKupca = getKupac(p).toLowerCase();
    const idPorudzbine = String(p.idPorudzbina || "").toLowerCase();
    const rezultatPretrage = imePrezimeKupca.includes(pretraga) || idPorudzbine.includes(pretraga);

    return ispravanStatus && rezultatPretrage;
  });

  const filtriraneOtpremnice = listaOtpremnica.filter((o) =>
    !otpremnicaFilter || (o.brojOtpremnice || "").toLowerCase().includes(otpremnicaFilter.toLowerCase())
  );

  const styles = {
    container: { minHeight: "100vh", backgroundColor: "#0b0f19", padding: "32px 24px", fontFamily: "Inter, sans-serif", color: "#f8fafc" },
    zaglavlje: { fontSize: "22px", fontWeight: "700", marginBottom: "20px", display: "flex", justifyContent: "space-between", alignItems: "center", color: "#ffffff" },
    meni: { display: "flex", gap: "8px", marginBottom: "24px", borderBottom: "1px solid #1e293b", paddingBottom: "12px" },
    tab: (aktivan) => ({ padding: "8px 16px", backgroundColor: aktivan ? "#3b82f6" : "#1e293b", color: aktivan ? "#fff" : "#94a3b8", border: "none", borderRadius: "6px", fontWeight: "500", cursor: "pointer", transition: "0.2s" }),
    
    sveSekcije: { display: "flex", flexDirection: "column", gap: "24px" },
    sekcijaForme: { backgroundColor: "#111827", border: "1px solid #1f2937", borderRadius: "10px", padding: "20px", boxShadow: "0 4px 6px -1px rgba(0,0,0,0.5)", width: "100%" },
    formaGrid: { display: "grid", gridTemplateColumns: "repeat(auto-fit, minmax(180px, 1fr))", gap: "16px", alignItems: "end", marginTop: "12px" },
    
    punaKartica: { backgroundColor: "#111827", border: "1px solid #1f2937", borderRadius: "10px", padding: "20px", boxShadow: "0 4px 6px -1px rgba(0,0,0,0.5)", width: "100%" },
    grupa: { marginBottom: "0px" },
    labela: { display: "block", fontSize: "12px", fontWeight: "500", color: "#9ca3af", marginBottom: "4px" },
    input: { width: "100%", padding: "8px 12px", fontSize: "14px", backgroundColor: "#1f2937", border: "1px solid #374151", color: "#f9fafb", borderRadius: "6px", outline: "none", boxSizing: "border-box" },
    dugme: { width: "100%", padding: "10px", backgroundColor: "#2563eb", color: "#fff", border: "none", borderRadius: "6px", fontWeight: "600", fontSize: "14px", cursor: "pointer" },
    tabela: { width: "100%", borderCollapse: "collapse", textAlign: "left" },
    th: { padding: "10px 12px", backgroundColor: "#1f2937", color: "#93c5fd", fontSize: "12px", fontWeight: "600", borderBottom: "1px solid #374151" },
    td: { padding: "10px 12px", borderBottom: "1px solid #1f2937", fontSize: "14px", color: "#e5e7eb", verticalAlign: "middle" },
    btnAkcija: { padding: "6px 12px", backgroundColor: "#1e293b", color: "#f8fafc", border: "1px solid #475569", borderRadius: "4px", fontSize: "12px", cursor: "pointer" },
    btnObrisi: { padding: "6px 12px", backgroundColor: "#7f1d1d", color: "#fca5a5", border: "1px solid #991b1b", borderRadius: "4px", fontSize: "12px", cursor: "pointer" }
  };

  return (
    <div style={styles.container}>
      {!jeZaposleni ? null : (
        <>
          <div style={styles.zaglavlje}>
            <span>Administracija</span>
            <span style={{ fontSize: "14px", color: "#94a3b8", fontWeight: "normal" }}>
              Zaposleni: {`${trenutniKorisnik.ime || ""} ${trenutniKorisnik.prezime || ""}`.trim() || trenutniKorisnik.Ime || ""}
            </span>
          </div>

          <div style={styles.meni}>

            <button style={styles.tab(trenutnaStranica === "proizvodi")} onClick={() => setTrenutnaStranica("proizvodi")}>Proizvodi</button>
            <button style={styles.tab(trenutnaStranica === "porudzbine")} onClick={() => setTrenutnaStranica("porudzbine")}>Porudžbine</button>
            <button style={styles.tab(trenutnaStranica === "otpremnice")} onClick={() => setTrenutnaStranica("otpremnice")}>Otpremnice</button>
            <button style={styles.tab(trenutnaStranica === "poreske")} onClick={() => setTrenutnaStranica("poreske")}>Poreske stope</button>

          </div>

          {trenutnaStranica === "proizvodi" && (
            <div style={styles.sveSekcije}>
              {!izmenaProizvoda ? (
                <div style={styles.sekcijaForme}>
                  <div style={{ fontWeight: "600", color: "#fff" }}>Dodajte / izmenite</div>
                  <div style={styles.formaGrid}>
                    <div style={styles.grupa}><label style={styles.labela}>Naziv</label><input value={formProizvod.naziv} onChange={(e) => setFormProizvod({ ...formProizvod, naziv: e.target.value })} style={styles.input} /></div>
                    <div style={styles.grupa}><label style={styles.labela}>Opis</label><input value={formProizvod.opis} onChange={(e) => setFormProizvod({ ...formProizvod, opis: e.target.value })} style={styles.input} /></div>
                    <div style={styles.grupa}><label style={styles.labela}>Slika URL</label><input value={formProizvod.slika} onChange={(e) => setFormProizvod({ ...formProizvod, slika: e.target.value })} style={styles.input} /></div>
                    <div style={styles.grupa}><label style={styles.labela}>Cena</label><input type="number" value={formProizvod.cena} onChange={(e) => setFormProizvod({ ...formProizvod, cena: e.target.value })} style={styles.input} /></div>
                    <div style={styles.grupa}><label style={styles.labela}>Količina na stanju</label><input type="number" value={formProizvod.kolicinaNaStanju} onChange={(e) => setFormProizvod({ ...formProizvod, kolicinaNaStanju: e.target.value })} style={styles.input} /></div>
                    <div style={styles.grupa}><label style={styles.labela}>Marka</label><input value={formProizvod.marka} onChange={(e) => setFormProizvod({ ...formProizvod, marka: e.target.value })} style={styles.input} /></div>
                    
                    <div style={styles.grupa}>
                      <label style={styles.labela}>Kategorija pol</label>
                      <select value={formProizvod.kategorijaPol} onChange={(e) => setFormProizvod({ ...formProizvod, kategorijaPol: e.target.value })} style={styles.input}>
                        <option value="">Izaberite pol</option>
                        <option value="z">Ženski</option>
                        <option value="m">Muški</option>
                        <option value="u">Univerzalni</option>
                      </select>
                    </div>

                    <div style={styles.grupa}>
                      <label style={styles.labela}>Kategorija mirisa</label>
                      <select value={formProizvod.idKategorijaMirisa} onChange={(e) => setFormProizvod({ ...formProizvod, idKategorijaMirisa: e.target.value })} style={styles.input}>
                        <option value="">Izaberite miris</option>
                        {listaKategorijaMirisa.map((m) => (
                          <option key={m.idKategorijaMirisa || m.id} value={m.idKategorijaMirisa || m.id}>
                            {m.naziv}
                          </option>
                        ))}
                      </select>
                    </div>

                    <div style={styles.grupa}>
                      <label style={styles.labela}>Poreska stopa</label>
                      <select value={formProizvod.idPoreskaStopa} onChange={(e) => setFormProizvod({ ...formProizvod, idPoreskaStopa: e.target.value })} style={styles.input}>
                        <option value="">Izaberite stopu</option>
                        {listaPoreskihStopa.map((s) => <option key={s.idPoreskaStopa} value={s.idPoreskaStopa}>{s.naziv} - {s.procenat}%</option>)}
                      </select>
                    </div>

                    <div>
                      <button onClick={sacuvajProizvod} style={styles.dugme}>Sačuvaj</button>
                    </div>
                  </div>
                </div>
              ) : (
                <div style={styles.sekcijaForme}>
                  <div style={{ fontWeight: "600", color: "#fff" }}>Izmeni proizvod</div>
                  <div style={styles.formaGrid}>
                    <div style={styles.grupa}><label style={styles.labela}>Naziv</label><input value={izmenaProizvoda.naziv || ""} onChange={(e) => setIzmenaProizvoda({ ...izmenaProizvoda, naziv: e.target.value })} style={styles.input} /></div>
                    <div style={styles.grupa}><label style={styles.labela}>Opis</label><input value={izmenaProizvoda.opis || ""} onChange={(e) => setIzmenaProizvoda({ ...izmenaProizvoda, opis: e.target.value })} style={styles.input} /></div>
                    <div style={styles.grupa}><label style={styles.labela}>Slika URL</label><input value={izmenaProizvoda.slika || ""} onChange={(e) => setIzmenaProizvoda({ ...izmenaProizvoda, slika: e.target.value })} style={styles.input} /></div>
                    <div style={styles.grupa}><label style={styles.labela}>Cena</label><input type="number" value={izmenaProizvoda.cena || ""} onChange={(e) => setIzmenaProizvoda({ ...izmenaProizvoda, cena: e.target.value })} style={styles.input} /></div>
                    <div style={styles.grupa}><label style={styles.labela}>Količina na stanju</label><input type="number" value={izmenaProizvoda.kolicinaNaStanju ?? ""} onChange={(e) => setIzmenaProizvoda({ ...izmenaProizvoda, kolicinaNaStanju: e.target.value })} style={styles.input} /></div>
                    <div style={styles.grupa}><label style={styles.labela}>Marka</label><input value={izmenaProizvoda.marka || ""} onChange={(e) => setIzmenaProizvoda({ ...izmenaProizvoda, marka: e.target.value })} style={styles.input} /></div>
                    
                    <div style={styles.grupa}>
                      <label style={styles.labela}>Kategorija pol</label>
                      <select value={izmenaProizvoda.kategorijaPol || ""} onChange={(e) => setIzmenaProizvoda({ ...izmenaProizvoda, kategorijaPol: e.target.value })} style={styles.input}>
                        <option value="">Izaberite pol</option>
                        <option value="Ženski">Ženski</option>
                        <option value="Muški">Muški</option>
                        <option value="Unisex">Unisex</option>
                      </select>
                    </div>

                    <div style={styles.grupa}>
                      <label style={styles.labela}>Kategorija mirisa</label>
                      <select value={izmenaProizvoda.idKategorijaMirisa || izmenaProizvoda.kategorijaMirisa?.idKategorijaMirisa || ""} onChange={(e) => setIzmenaProizvoda({ ...izmenaProizvoda, idKategorijaMirisa: e.target.value })} style={styles.input}>
                        <option value="">Izaberite miris</option>
                        {listaKategorijaMirisa.map((m) => (
                          <option key={m.idKategorijaMirisa || m.id} value={m.idKategorijaMirisa || m.id}>
                            {m.naziv}
                          </option>
                        ))}
                      </select>
                    </div>

                    <div style={styles.grupa}>
                      <label style={styles.labela}>Poreska stopa</label>
                      <select value={izmenaProizvoda.idPoreskaStopa || ""} onChange={(e) => setIzmenaProizvoda({ ...izmenaProizvoda, idPoreskaStopa: e.target.value })} style={styles.input}>
                        <option value="">Izaberite stopu</option>
                        {listaPoreskihStopa.map((s) => <option key={s.idPoreskaStopa} value={s.idPoreskaStopa}>{s.naziv} - {s.procenat}%</option>)}
                      </select>
                    </div>

                    <div style={{ display: "flex", gap: "8px" }}>
                      <button onClick={izmeniProizvod} style={styles.dugme}>Sačuvaj izmene</button>
                      <button onClick={() => setIzmenaProizvoda(null)} style={{ ...styles.dugme, backgroundColor: "#334155" }}>Otkaži</button>
                    </div>
                  </div>
                </div>
              )}

              <div style={styles.punaKartica}>
                <div style={{ fontWeight: "600", marginBottom: "16px", color: "#fff" }}>Lista proizvoda</div>
                <table style={styles.tabela}>
                  <thead>
                    <tr>
                      <th style={styles.th}>Naziv</th>
                      <th style={styles.th}>Marka</th>
                      <th style={styles.th}>Pol</th>
                      <th style={styles.th}>Miris</th>
                      <th style={styles.th}>Cena</th>
                      <th style={styles.th}>Količina na stanju</th>
                      <th style={styles.th}>Akcije</th>
                    </tr>
                  </thead>
                  <tbody>
                    {listaProizvoda.map((p) => (
                      <tr key={p.idProizvod}>
                        <td style={styles.td}>{p.naziv}</td>
                        <td style={styles.td}>{p.marka || "-"}</td>
                        <td style={styles.td}>{p.kategorijaPol || "-"}</td>
                        <td style={styles.td}>{listaKategorijaMirisa.find(m => m.idKategorijaMirisa === p.idKategorijaMirisa)?.naziv || "-"}</td>
                        <td style={styles.td}>{p.cena} RSD</td>
                        <td style={styles.td}>{p.kolicinaNaStanju}</td>
                        <td style={styles.td}>
                          <button onClick={() => setIzmenaProizvoda(p)} style={{ ...styles.btnAkcija, marginRight: "6px" }}>Izmeni</button>
                          <button onClick={() => obrisiProizvod(p.idProizvod)} style={styles.btnObrisi}>Obriši</button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          )}

          {trenutnaStranica === "poreske" && (
            <div style={styles.sveSekcije}>
              <div style={styles.sekcijaForme}>
                <div style={{ fontWeight: "600", color: "#fff" }}>Nova poreska stopa</div>
                <div style={styles.formaGrid}>
                  <div style={styles.grupa}>
                    <label style={styles.labela}>Naziv</label>
                    <input value={formPoreskaStopa.naziv} onChange={(e) => setFormPoreskaStopa({ ...formPoreskaStopa, naziv: e.target.value })} style={styles.input} />
                  </div>
                  <div style={styles.grupa}>
                    <label style={styles.labela}>Procenat (%)</label>
                    <input type="number" value={formPoreskaStopa.procenat} onChange={(e) => setFormPoreskaStopa({ ...formPoreskaStopa, procenat: e.target.value })} style={styles.input} />
                  </div>
                  <div>
                    <button onClick={sacuvajPoreskuStopu} style={styles.dugme}>Dodaj</button>
                  </div>
                </div>
              </div>

              <div style={styles.punaKartica}>
                <div style={{ fontWeight: "600", marginBottom: "16px", color: "#fff" }}>Poreske stope</div>
                <table style={styles.tabela}>
                  <thead>
                    <tr>
                      <th style={styles.th}>ID</th>
                      <th style={styles.th}>Naziv</th>
                      <th style={styles.th}>Procenat</th>
                    </tr>
                  </thead>
                  <tbody>
                    {listaPoreskihStopa.map((s) => (
                      <tr key={s.idPoreskaStopa}>
                        <td style={styles.td}>#{s.idPoreskaStopa}</td>
                        <td style={styles.td}>{s.naziv}</td>
                        <td style={styles.td}>{s.procenat}%</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          )}

          {trenutnaStranica === "porudzbine" && (
            <div style={styles.punaKartica}>
              <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: "16px" }}>
                <div style={{ fontWeight: "600", color: "#fff" }}>Porudžbine</div>
              </div>

              <div style={{ display: "flex", gap: "12px", marginBottom: "16px" }}>
                <input placeholder="Pretraži kupca" value={kupacFilter} onChange={(e) => setKupacFilter(e.target.value)} style={{ ...styles.input, maxWidth: "240px" }} />
                <select value={statusFilter} onChange={(e) => setStatusFilter(e.target.value)} style={{ ...styles.input, maxWidth: "200px" }}>
                  <option value="">Svi statusi</option>
                  <option value="KREIRANA">KREIRANA</option>
                  <option value="ISPORUCENA">ISPORUCENA</option>
                  <option value="OTKAZANA">OTKAZANA</option>
                </select>
              </div>

              <table style={styles.tabela}>
                <thead>
                  <tr>
                    <th style={styles.th}>ID</th>
                    <th style={styles.th}>Kupac</th>
                    <th style={styles.th}>Status porudžbine</th>
                    <th style={styles.th}>Status isporuke</th>
                    <th style={styles.th}>Ukupan iznos</th>
                    <th style={styles.th}>Popust</th>
                    <th style={styles.th}>Stavke</th>
                    <th style={styles.th}>Akcija</th>
                  </tr>
                </thead>
                <tbody>
                  {filtriranePorudzbine.length === 0 ? (
                    <tr>
                      <td colSpan="8" style={{ ...styles.td, textAlign: "center", color: "#94a3b8", padding: "20px" }}>
                        Nema porudžbina koje odgovaraju kriterijumima.
                      </td>
                    </tr>
                  ) : (
                    filtriranePorudzbine.map((p) => (
                      <React.Fragment key={p.idPorudzbina}>
                        <tr>
                          <td style={styles.td}>#{p.idPorudzbina}</td>
                          <td style={styles.td}>{getKupac(p)}</td>
                          <td style={styles.td}>{p.statusPorudzbine}</td>
                          <td style={styles.td}>{p.statusIsporuke}</td>
                          <td style={styles.td}>{p.ukupanIznos} Rsd</td>
                          
                          <td style={styles.td}>
                            {izmeniPorudzbine === p.idPorudzbina ? (
                              <div style={{ display: "flex", gap: "5px", alignItems: "center" }}>
                                <input 
                                  type="number" 
                                  value={izmenaPopusta} 
                                  onChange={(e) => setIzmenaPopusta(e.target.value)} 
                                  placeholder="%"
                                  style={{ ...styles.input, width: "60px", padding: "4px" }}
                                />
                                <button onClick={() => sacuvajPopustPorudzbine(p.idPorudzbina)} style={{ ...styles.btnAkcija, backgroundColor: "#2563eb", color: "#fff" }}>✓</button>
                                <button onClick={() => setIzmeniPorudzbine(null)} style={styles.btnObrisi}>✕</button>
                              </div>
                            ) : (
                              <div style={{ display: "flex", gap: "8px", alignItems: "center" }}>
                                <span>{p.popust ? `${p.popust}%` : '0%'}</span>
                                <button onClick={() => { setIzmeniPorudzbine(p.idPorudzbina); setIzmenaPopusta(p.popust || ''); }} style={styles.btnAkcija}>
                                  Izmeni
                                </button>
                              </div>
                            )}
                          </td>

                          <td style={styles.td}>
                            <button onClick={() => setSelektovanaPorudzbina(selektovanaPorudzbina === p.idPorudzbina ? null : p.idPorudzbina)} style={styles.btnAkcija}>
                              {selektovanaPorudzbina === p.idPorudzbina ? "Zatvori" : "Stavke"}
                            </button>
                          </td>

                          <td style={styles.td}>
                            {p.statusPorudzbine !== "OTKAZANA" && p.statusIsporuke !== "POTPUNO_ISPORUCENO" ? (
                              <button onClick={() => otkaziPorudzbinu(p.idPorudzbina)} style={styles.btnObrisi}>
                                Otkaži porudžbinu
                              </button>
                            ) : (
                              <span style={{ color: "#64748b", fontSize: "12px" }}>Nije dostupno</span>
                            )}
                          </td>
                        </tr>

                        {selektovanaPorudzbina === p.idPorudzbina && (
                        <tr>
                            <td colSpan="8" style={{ ...styles.td, backgroundColor: "#0f172a" }}>
                            <div style={{ fontSize: "13px", fontWeight: "600", marginBottom: "8px", color: "#93c5fd" }}>Stavke i otprema:</div>
                            {p.stavke && p.stavke.map((s) => {
                                const vecIsporuceno = izracunajIsporuceno(p.idPorudzbina, s.idProizvod);
                                return (
                                <div key={s.rb} style={{ display: "flex", alignItems: "center", gap: "10px", marginBottom: "6px" }}>
                                    <span style={{ fontSize: "13px", color: "#cbd5e1" }}>
                                    {s.nazivProizvoda} (Naručeno: {s.kolicina}, Isporučeno: {vecIsporuceno})
                                    </span>
                                    <input
                                    type="number"
                                    placeholder="Količina"
                                    max={s.kolicina - vecIsporuceno}
                                    min={0}
                                    disabled={p.statusIsporuke === "POTPUNO_ISPORUCENO"}
                                    style={{ ...styles.input, width: "80px", padding: "4px 8px" }}
                                    onChange={(e) => setStavkeOtpremniceMap({ ...stavkeOtpremniceMap, [s.idProizvod]: e.target.value })}
                                    />
                                </div>
                                );
                            })}
                            <button
                                onClick={() => generisiOtpremnicu(p.idPorudzbina, p.stavke)}
                                disabled={p.statusIsporuke === "POTPUNO_ISPORUCENO"}
                                style={{ ...styles.dugme, maxWidth: "180px", marginTop: "8px", fontSize: "12px", padding: "6px" }}
                            >
                                Kreiraj otpremnicu
                            </button>
                            </td>
                        </tr>
                        )}
                      </React.Fragment>
                    ))
                  )}
                </tbody>
              </table>
            </div>
          )}

          {trenutnaStranica === "otpremnice" && (
            <div style={styles.punaKartica}>
              <div style={{ fontWeight: "600", marginBottom: "16px", color: "#fff" }}>Evidencija otpremnica</div>
              <input
                placeholder="Pretraži po broju otpremnice..."
                value={otpremnicaFilter}
                onChange={(e) => setOtpremnicaFilter(e.target.value)}
                style={{ ...styles.input, maxWidth: "240px", marginBottom: "16px" }}
              />
              <table style={styles.tabela}>
                <thead>
                  <tr>
                    <th style={styles.th}>Broj</th>
                    <th style={styles.th}>Datum</th>
                    <th style={styles.th}>Porudžbina</th>
                    <th style={styles.th}>Status</th>
                    <th style={styles.th}>Iznos</th>
                    <th style={styles.th}>Akcija</th>
                  </tr>
                </thead>
                <tbody>
                  {filtriraneOtpremnice.map((o) => (
                    <tr key={o.idOtpremnica}>
                      <td style={styles.td}>{o.brojOtpremnice}</td>
                      <td style={styles.td}>{o.datumIzdavanja}</td>
                      <td style={styles.td}>#{o.idPorudzbina}</td>
                      <td style={styles.td}>{o.status}</td>
                      <td style={styles.td}>{o.iznos} Rsd</td>
                      <td style={styles.td}>
                        {o.status !== "OTKAZANA" && (
                          <button onClick={() => otkaziOtpremnicu(o.idOtpremnica)} style={styles.btnObrisi}>Otkaži</button>
                        )}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </>
      )}
    </div>
  );
}

export default Administracija;