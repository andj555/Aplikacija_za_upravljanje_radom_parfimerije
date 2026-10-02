import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import PojedinacanProizvod from "./pages/PojedinacanProizvod";
import Navbar from "./components/Navbar";
import Prijava from "./pages/Prijava";
import Pocetna from "./pages/Pocetna";
import Registracija from "./pages/Registracija";
import ZavrsiRegistraciju from "./pages/ZavrsiRegistraciju";
import Proizvodi from "./pages/Proizvodi";
import Korpa from "./pages/Korpa";
import Porudzbine from "./pages/Porudzbine";
import Administracija from "./pages/Administracija";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Prijava />} />
        <Route path="/prijava" element={<Prijava />} />
        <Route path="/pocetna" element={<Pocetna />} />
        <Route path="/registracija" element={<Registracija />} />
        <Route path="/verifikacija" element={<ZavrsiRegistraciju />} />
        <Route path="/proizvodi" element={<Proizvodi />} />
        <Route path="/korpa" element={<Korpa />} />
        <Route path="/porudzbine" element={<Porudzbine />} />
        <Route path="/proizvodi/:idProizvod" element={<PojedinacanProizvod />} />
        <Route path="/administracija" element={<Administracija />} />

      </Routes>
    </BrowserRouter>
  );
}


export default App;
