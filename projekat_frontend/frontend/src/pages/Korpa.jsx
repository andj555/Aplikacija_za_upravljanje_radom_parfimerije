import React, { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import { porudzbinaApi } from "../api";

function Korpa() {

  const [korpa, setKorpa] =useState([]);
  const [uvecanaSlika, setUvecanaSlika] = useState(null);
  const [adresaIsporuke, setAdresaIsporuke] = useState("");
  const ukupanIznos = korpa.reduce((sum, p) => {
    const cenaBezPdv = p.cena || 0;
    const procenatPdva = p.poreskaStopa?.procenat || 20.0;
    const cenaSaPdv = cenaBezPdv * (1 + procenatPdva / 100.0);
    return sum + (cenaSaPdv * p.kolicina);
  }, 0);

  useEffect(() => {
    const postojecaKorpa = JSON.parse(localStorage.getItem("korpa")) || [];
    setKorpa(postojecaKorpa);
  }, []);
    const azurirajKolicinu = (idProizvoda, novaKolicina) => {
      const azuriranaKorpa = korpa.map((p) => {
        if (p.idProizvod === idProizvoda) {
          return { ...p, kolicina: novaKolicina };
        }
        return p;
      });
      setKorpa(azuriranaKorpa);
      localStorage.setItem("korpa", JSON.stringify(azuriranaKorpa));
    };

    const ukloniIzKorpe = (idProizvoda) => {
    const filtriranaKorpa = korpa.filter((p) => p.idProizvod !== idProizvoda);
    setKorpa(filtriranaKorpa);
    localStorage.setItem("korpa", JSON.stringify(filtriranaKorpa));
  };
  const promeniKolicinu = (idProizvoda, novaKolicina) => {
    if (novaKolicina <= 0) {
      ukloniIzKorpe(idProizvoda);
    } else {
      
    azurirajKolicinu(idProizvoda, novaKolicina);
  }
};

  const kreirajPorudzbinu = async () => {
    const korisnik = JSON.parse(localStorage.getItem("korisnik"));
    if (!korisnik) {
      alert("Morate biti prijavljeni");
      return;
    }

    if (!adresaIsporuke.trim()) {
      alert("Morate uneti adresu isporuke");
      return;
    }

    const stavke = korpa.map((item, index) => ({
      rb: index + 1,
      kolicina: item.kolicina || 1,
      cenaPoKomadu: item.cena,
      iznos: item.cena * (item.kolicina || 1),
      idProizvod: item.idProizvod,
    }));

    const porudzbina = {
      datum: new Date().toISOString().split("T")[0],
      statusPorudzbine: "KREIRANA",
      statusIsporuke: "NEISPORUCENO",
      adresaIsporuke: adresaIsporuke,
      ukupanIznos: ukupanIznos,
      korisnik: { 
        idKorisnik: korisnik.idKorisnik }
      };

    console.log("salje se :", { porudzbina, stavke });
    try {
      const response = await porudzbinaApi.poruci({ porudzbina, stavke });
      console.log("response:", response.data);
      localStorage.removeItem("korpa");
      setKorpa([]);
      alert("Porudžbina je uspešno kreirana");
    } catch (error) {
      console.error(" catch blok :", error.response);
      alert("Greska: " + JSON.stringify(error.response?.data || error.message));
    }
  }

  return (
    <div style={{ padding: "40px", fontFamily: "sans-serif", color: "#fff", maxWidth: "800px", margin: "0 auto" }}>
      <h2 style={{ borderBottom: "2px solid #333", paddingBottom: "10px", marginBottom: "20px" }}>Moja korpa</h2>

      {korpa.length === 0 ? (
        <div>
          <p style={{ color: "#aaa" }}>Vasa korpa je prazna</p>
          <Link to="/" style={{ color: "#007bff" }}>Nazad na početnu stranicu</Link>
        </div>
      ) : (
        <div>

          {korpa.map((proizvod) => {
            const cenaBezPdv = proizvod.cena || 0;
            const procenatPdva = proizvod.poreskaStopa?.procenat || 20.0;
            const cenaSaPdv = (cenaBezPdv * (1 + procenatPdva / 100.0));

            return (
            <div 
              key={proizvod.idProizvod} 
              style={{ 
                display: "flex", 
                justifyContent: "space-between", 
                alignItems: "center", 
                background: "#1e1e1e", 
                padding: "15px", 
                marginBottom: "10px", 
                borderRadius: "8px", 
                border: "1px solid #333" 
              }}
            >
              <div style={{ display: "flex", alignItems: "center", gap: "15px" }}>
                
                <img 
                  src={proizvod.slika} 
                  alt={proizvod.naziv} 
                  onClick={() => setUvecanaSlika(proizvod.slika)}
                  style= {{ 
                    width: "60px", 
                    height: "60px", 
                    objectFit: "cover", 
                    borderRadius: "4px",
                    border: "1px solid #333",
                    cursor: "pointer"
                  }}
                />
                <div>
                  <h4 style={{ margin: "0 0 5px 0", fontSize: "18px" }}>{proizvod.naziv}</h4>
                  <p style={{ margin: 0, color: "#4caf50", fontWeight: "bold" }}>{cenaSaPdv} RSD</p>
                </div>
              </div>

              <div style={{ display: "flex", alignItems: "center", gap: "8px" }}>
             <span>Količina: {proizvod.kolicina}</span>
             <button onClick={() => promeniKolicinu(proizvod.idProizvod, proizvod.kolicina - 1)}>-</button>
            <button onClick={() => promeniKolicinu(proizvod.idProizvod, proizvod.kolicina + 1)}>+</button>
          </div>
          </div>
        );
        })}

          <div style={{ marginTop: "30px", borderTop: "2px solid #333", paddingTop: "20px" }}>
            <input 
              type="text" 
              value={adresaIsporuke} 
              onChange={(e) => setAdresaIsporuke(e.target.value)}
              placeholder="Unesite adresu isporuke" 
              style={{ width: "100%", padding: "10px", marginBottom: "15px", borderRadius: "4px", border: "1px solid #444", background: "#222", color: "#fff", boxSizing: "border-box" }}
            />

            <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
              <h3 style={{ margin: 0 }}>Ukupan iznos: <span style={{ color: "#4caf50" }}>{Math.round(ukupanIznos)} Rsd</span></h3>
              <button onClick={kreirajPorudzbinu} style={{ padding: "10px 20px", background: "#0d5c85", color: "#fff", border: "none", borderRadius: "4px", cursor: "pointer", fontWeight: "bold" }}>
                Potvrdite porudžbinu
              </button>

            </div>
          </div>

      </div>
      )}
      {uvecanaSlika && (
        <div 
          onClick={() => setUvecanaSlika(null)} 
          style={{
            position: "fixed",
            top: 0,
            left: 0,
            width: "100vw",
            height: "100vh",
            backgroundColor: "rgba(0, 0, 0, 0.8)",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            zIndex: 1000,
            cursor: "pointer"
          }}
        >
          <img 
            src={uvecanaSlika} 
            alt="Uvecana slika" 
            style={{
              maxWidth: "80%",
              maxHeight: "80%",
              borderRadius: "8px",
              objectFit: "contain",
              border: "2px solid #555"
            }}
          />
        </div>
      )}

    </div>
  );
}

export default Korpa;