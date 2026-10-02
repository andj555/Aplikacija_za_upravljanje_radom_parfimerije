import React, { useState, useEffect } from "react";
import { porudzbinaApi, otpremnicaApi } from "../api";

function Porudzbine() {
  const [porudzbine, setPorudzbine] = useState([]);
  const [otpremnice, setOtpremnice] = useState([]);
  const [uvecanaSlika, setUvecanaSlika] = useState(null);

  useEffect(() => {
    const korisnik = JSON.parse(localStorage.getItem("korisnik") || "{}");
    
    porudzbinaApi.getByKorisnik(korisnik.idKorisnik).then((r) => {
      setPorudzbine(r.data);
    }).catch((err) => console.error("Greška tokom učitavanja porudžbina: ", err));

    otpremnicaApi.getAll().then((r) => {
      setOtpremnice(r.data);
    }).catch((err) => console.error("Greška tokom učitavanja otpremnica: ", err));
  }, []);


  const style = {
    container: { padding: "20px", fontFamily: "sans-serif" },
    header: { fontSize: "20px", fontWeight: "bold", marginBottom: "16px" },
    card: { border: "1px solid #ccc", padding: "16px", marginBottom: "12px", borderRadius: "6px" },
    row: { display: "flex", justifyContent: "space-between", marginBottom: "6px" },
    item: { fontSize: "14px", marginLeft: "10px", color: "#ffffff", display: "flex", alignItems: "center", gap: "8px", marginBottom: "4px" },
    button: { padding: "4px 8px", cursor: "pointer" }
  };

  return (
    <div style={style.container}>
      <h2 style={style.header}>Moje porudžbine</h2>
      {porudzbine.length === 0 && <p>Nemate nijednu porudzbinu</p>}
      {porudzbine.map((p) => {
        const otpremnicePorudzbine = otpremnice.filter((o) => o.idPorudzbina === p.idPorudzbina);
        return (
          <div key={p.idPorudzbina} style={style.card}>
            <div style={style.row}><strong>Porudžbina #{p.idPorudzbina}</strong><span>{p.datum || "-"}</span></div>
            <div style={style.row}><span>Status: {p.statusPorudzbine}</span><span>Isporuka: {p.statusIsporuke}</span></div>
            <div style={style.row}><span>Ukupan iznos: {p.ukupanIznos} Rsd</span></div>
            
           <div style={{ marginTop: "10px", marginBottom: "10px" }}>
              <div style={{ fontWeight: "bold", fontSize: "13px", marginBottom: "4px" }}>Stavke:</div>
              {p.stavke && p.stavke.map((s) => {
                console.log("Stavka iz baze:", s);
                return (
                  <div key={s.rb} style={style.item}>
                    {s.slikaProizvoda && (
                      <img 
                        src={s.slikaProizvoda} 
                        alt="" 
                        style={{ width: "30px", height: "30px", objectFit: "cover", borderRadius: "4px", cursor: "pointer" }}
                        onClick={() => setUvecanaSlika(s.slikaProizvoda)}
                      />
                    )}
                    <span>- { s.nazivProizvoda || "Proizvod"} ({s.kolicina} kom.) — {s.iznos} Rsd</span>
                  </div>
                );
              })}
            </div>

            {otpremnicePorudzbine.length > 0 && (
              <div style={{ marginTop: "10px", borderTop: "1px solid #eee", paddingTop: "8px" }}>
                <div style={{ fontWeight: "bold", fontSize: "13px", marginBottom: "4px" }}>Otpremnice:</div>
                {otpremnicePorudzbine.map((o) => (
                  <div key={o.idOtpremnica} style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: "4px" }}>
                    <span>Broj: {o.brojOtpremnice} | {o.iznos} RSD ({o.status})</span>
                  </div>
                ))}
              </div>
            )}
          </div>
        );
      })}

      {uvecanaSlika && (
        <div 
          onClick={() => setUvecanaSlika(null)} 
          style={{
            position: "fixed", top: 0, left: 0, width: "100%", height: "100%",
            backgroundColor: "rgba(0,0,0,0.7)", display: "flex", justifyContent: "center",
            alignItems: "center", zIndex: 1000, cursor: "pointer"
          }}
        >
          <img 
            src={uvecanaSlika} 
            alt="Uvecana slika" 
            style={{ maxWidth: "80%", maxHeight: "80%", borderRadius: "8px", boxShadow: "0 4px 10px rgba(0,0,0,0.3)" }} 
          />
        </div>
      )}
    </div>
  );
}

export default Porudzbine;