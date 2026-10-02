import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { proizvodApi, kategorijaMirisaApi } from "../api";

function Proizvodi() {
  const [proizvodi, setProizvodi] = useState([]);
  const [kategorije, setKategorije] = useState([]);
  const [pretraga, setPretraga] = useState("");
  const [izabraniPol, setIzabraniPol] = useState("");
  const [izabranaKategorijaM, setIzabranaKategorijaM] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const filtriraniProizvodi = proizvodi.filter(product => {
    const p = pretraga.toLowerCase().trim();
    const odgovaraPretrazi = !p || (product.naziv || "")
      .toLowerCase()
      .split(" ")
      .some((res) => res.startsWith(p));
   
    const odgovaraPolu = izabraniPol === "" || (product.kategorijaPol && String(product.kategorijaPol).trim().toLowerCase() === izabraniPol.trim().toLowerCase());
    
    const izabranaKatM = kategorije.find(k => String(k.naziv) === String(izabranaKategorijaM));
    const nazivKatM = typeof product.kategorijaMirisa === 'object' ? product.kategorijaMirisa?.naziv : product.kategorijaMirisa;
    
    const odgovaraKategoriji = izabranaKategorijaM === "" || String(nazivKatM) === String(izabranaKategorijaM) ||
      (izabranaKatM && (product.idKategorijaMirisa === izabranaKatM.idKategorijaMirisa || product.kategorijaMirisa === izabranaKatM.idKategorijaMirisa));

    return odgovaraPretrazi && odgovaraPolu && odgovaraKategoriji;
  });

  useEffect(() => {
    Promise.all([
      proizvodApi.getAll(),
      kategorijaMirisaApi?.getAll ? kategorijaMirisaApi.getAll() : Promise.resolve({ data: [] })
    ])
      .then(([respProizvodi, respMirisi]) => {
        setProizvodi(respProizvodi.data || []);
         setKategorije(respMirisi.data || []);
        setLoading(false);
      })
      .catch((err) => {
        console.error("GRESKA:", err);
        setError("Greska pri ucitavanju");
        setLoading(false);
      });
  }, []);

  if (loading) return <p style={{ padding: "40px", color: "#fff", textAlign: "center" }}>Ucitavanje</p>;
  if (error) return <p style={{ padding: "40px", color: "red", textAlign: "center" }}>{error}</p>;

  return (

    <div style={{ padding: "40px", fontFamily: "sans-serif", color: "#fff", maxWidth: "1200px", margin: "0 auto" }}>
      <h2 style={{ fontSize: "32px", marginBottom: "20px", borderBottom: "2px solid #333", paddingBottom: "10px" }}>
        Svi proizvodi
      </h2>

      <div style={{ marginBottom: "30px", display: "flex", gap: "15px", flexWrap: "wrap", alignItems: "center" }}>
        <input 
          type="text"
          placeholder="Pretraži..."
          value={pretraga}
          onChange={(e) => setPretraga(e.target.value)}
          style={{
            width: "350px",
            maxWidth: "100%",
            padding: "12px 15px",
            borderRadius: "8px",
            border: "1px solid #444",
            backgroundColor: "#1e1e1e",
            color: "#fff",
            fontSize: "16px",
            outline: "none",
            boxSizing: "border-box",
            flexShrink: 0
          }}
        />

        <select 
          value={izabraniPol} 
          onChange={(e) => setIzabraniPol(e.target.value)}
          style={{ width: "150px", padding: '12px 8px', borderRadius: '4px', border: '1px solid #ccc', backgroundColor: '#1e1e1e', color: '#fff', boxSizing: 'border-box', flexShrink: 0 }}
        >
          <option value="">Svi polovi</option>
          <option value="m">Muški</option>
          <option value="z">Ženski</option>
          <option value="u">Univerzalni</option>
        </select>

        <select 
          value={izabranaKategorijaM} 
          onChange={(e) => setIzabranaKategorijaM(e.target.value)}
          style={{ width: "220px", padding: '12px 8px', borderRadius: '4px', border: '1px solid #ccc', backgroundColor: '#1e1e1e', color: '#fff', boxSizing: 'border-box', flexShrink: 0 }}
        >
          <option value="">Sve kategorije mirisa</option>
          {kategorije.map((kat) => (

            <option key={kat.idKategorijaMirisa} value={kat.naziv}>
              {kat.naziv}
            </option>
          ))}
        </select>
      </div>
      
      {filtriraniProizvodi.length === 0 ? (
        <p style={{ color: "#aaa" }}>Nema proizvoda koji odgovaraju pretrazi</p>
      ) : (
        <div style={{ display: "flex", flexWrap: "wrap", justifyContent: "center", gap: "25px" }}>
          {filtriraniProizvodi.map((proizvod) => {  
            const cenaBezPdv = proizvod.cena || 0;
            const procenatPdva = proizvod.poreskaStopa?.procenat || 20.0;
            const cenaSaPdv = cenaBezPdv * (1 + procenatPdva / 100.0);
            return ( 
              <div 
                key={proizvod.idProizvod} 
                style={{ 
                  width: "280px",
                  boxSizing: "border-box",
                  background: "#1e1e1e", 
                  border: "1px solid #333", 
                  padding: "20px", 
                  borderRadius: "12px", 
                  display: "flex", 
                  flexDirection: "column", 
                  justifyContent: "space-between",
                  alignItems: "center",
                  textAlign: "center",
                  boxShadow: "0 4px 10px rgba(0,0,0,0.3)",
                  flexShrink: 0
                }}
              >
                <div style={{ width: "100%" }}>
                  <div style={{ width: "100%", height: "200px", background: "#fff", borderRadius: "8px", display: "flex", alignItems: "center", justifyContent: "center", marginBottom: "15px", overflow: "hidden", boxSizing: "border-box" }}>
                    {proizvod.slika && proizvod.slika.startsWith("http") ? (
                      <img 
                        src={proizvod.slika} 
                        alt={proizvod.naziv} 
                        style={{ width: "100%", height: "100%", objectFit: "contain", padding: "10px", boxSizing: "border-box" }} 
                      />
                    ) : (
                      <span style={{ color: "#888" }}>Nema slike</span>
                    )}
                  </div>

                  <h3 style={{ margin: "0 0 10px 0", fontSize: "20px", color: "#fff", minHeight: "28px" }}>{proizvod.naziv}</h3>
                  <p style={{ fontSize: "18px", color: "#4caf50", fontWeight: "bold", margin: "0 0 15px 0" }}>
                    {cenaSaPdv} RSD
                  </p>
                </div>

                <Link to={`/proizvodi/${proizvod.idProizvod}`} style={{ textDecoration: "none", width: "100%" }}>
                  <button style={{ 
                    width: "100%", 
                    padding: "10px", 
                    cursor: "pointer", 
                    backgroundColor: "#007bff", 
                    color: "#fff", 
                    border: "none", 
                    borderRadius: "6px", 
                    fontSize: "15px",
                    fontWeight: "bold",
                    boxSizing: "border-box"
                  }}>
                    Detaljno
                  </button>
                </Link>
              </div>
            );
          })}
        </div>
      )}
    </div>
  );
}

export default Proizvodi;