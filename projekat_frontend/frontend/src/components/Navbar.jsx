import React from "react";
import { Link, useNavigate } from "react-router-dom";

function Navbar() {
  const navigate = useNavigate();
  const korisnik = JSON.parse(localStorage.getItem("korisnik") || "{}");
 const isUlogovan = !!korisnik.email;
  const tipKorisnika = korisnik.tipKorisnika;

  const isZaposleni = tipKorisnika === "ZAPOSLENI";
  const isKupac = tipKorisnika === "KUPAC";

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("korisnik");
    navigate("/prijava");
    window.location.reload();
  };

  const style = {
    nav: {
      backgroundColor: "#ffffff",
      borderBottom: "1px solid #e0e0e0",
      padding: "15px 30px",
      display: "flex",
      justifyContent: "space-between",
      alignItems: "center",
      fontFamily: "sans-serif",
    },
    logo: {
      fontSize: "20px",
      fontWeight: "bold",
      color: "#2c3e50",
      textDecoration: "none",
    },
    linksContainer: {
      display: "flex",
      gap: "20px",
      alignItems: "center",
    },
    link: {
      color: "#555555",
      textDecoration: "none",
      fontSize: "14px",
      fontWeight: "500",
    },
  };

  return (
    <nav style={style.nav}>
      <Link to={isUlogovan ? "/pocetna" : "/prijava"} style={style.logo}>Le perfumerie</Link>

      <div style={style.linksContainer}>
        {isUlogovan && (
          <>
            <Link to="/pocetna" style={style.link}>Početna</Link>
          </>
        )}

        {isKupac && (
          <>
           <Link to="/proizvodi" style={style.link}>Proizvodi</Link>
            <Link to="/korpa" style={style.link}>Korpa</Link>
            <Link to="/porudzbine" style={style.link}>Moje porudžbine</Link>
          </>
        )}

        {isZaposleni && (
          <Link to="/administracija" style={style.link}>Administracija</Link>
        )}

        {!isUlogovan ? (
          <>
          <Link to="/proizvodi" style={style.link}>Proizvodi</Link>
          <Link to="/registracija" style={style.link}>Registracija</Link>
          </>
        ) : (
          <button onClick={logout} style={{ ...style.link, background: "none", border: "none", cursor: "pointer" }}>
            Odjava
          </button>
        )}
      </div>
    </nav>
  );
}

export default Navbar;