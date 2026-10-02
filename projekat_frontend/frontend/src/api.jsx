import axios from "axios";

const API_URL = "http://localhost:8080/api";

const api = axios.create({ baseURL: API_URL });

api.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  console.log(`Slanje zahteva na [${config.method.toUpperCase()}] ${config.url} sa tokenom:`, token);
  console.log("token :", token);
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export const authApi = {
  prijava: (email, password) => api.post("/auth/prijava", { email, password }),
  registracija: (korisnik) => api.post("/auth/posalji-verifikaciju", korisnik),
  verifikacija: (token) => api.get(`/auth/verifikacija/${token}`),
  zavrsiRegistraciju: (podaci) => api.post("/auth/zavrsi-registraciju", podaci),
};

export const proizvodApi = {

  getAll: () => api.get("/proizvodi"),
  getById: (idProizvod) => api.get(`/proizvodi/${idProizvod}`),
  save: (proizvod) => api.post("/proizvodi", proizvod),
  update: (idProizvod, proizvod) => api.put(`/proizvodi/${idProizvod}`, proizvod),
  delete: (idProizvod) => api.delete(`/proizvodi/${idProizvod}`),
};

export const poreskaStopaApi = {
  getAll: () => api.get("/poreske-stope"),
  getById: (idPoreskaStopa) => api.get(`/poreske-stope/${idPoreskaStopa}`),
  save: (stopa) => api.post("/poreske-stope", stopa),
  update: (idPoreskaStopa, stopa) => api.put(`/poreske-stope/${idPoreskaStopa}`, stopa),
  delete: (idPoreskaStopa) => api.delete(`/poreske-stope/${idPoreskaStopa}`),
};

export const kategorijaMirisaApi = {
  getAll: () => api.get("/kategorije-mirisa"),
  getById: (idKategorijaMirisa) => api.get(`/kategorije-mirisa/${idKategorijaMirisa}`),
  save: (kategorija) => api.post("/kategorije-mirisa", kategorija),
  update: (idKategorijaMirisa, kategorija) => api.put(`/kategorije-mirisa/${idKategorijaMirisa}`, kategorija),
  delete: (idKategorijaMirisa) => api.delete(`/kategorije-mirisa/${idKategorijaMirisa}`),
};

export const korisnikApi = {
  getAll: () => api.get("/korisnici"),
  getById: (idKorisnik) => api.get(`/korisnici/${idKorisnik}`),
  save: (korisnik) => api.post("/korisnici", korisnik),
  update: (idKorisnik, korisnik) => api.put(`/korisnici/${idKorisnik}`, korisnik),
  delete: (idKorisnik) => api.delete(`/korisnici/${idKorisnik}`),
};

export const porudzbinaApi = {
  getAll: () => api.get("/porudzbine"),
  getById: (idPorudzbina) => api.get(`/porudzbine/${idPorudzbina}`),
  getByKorisnik: (idKorisnik) => api.get(`/porudzbine/korisnik/${idKorisnik}`),
  poruci: (zahtev) => api.post("/porudzbine/poruci", zahtev),
  updatePopust: (idPorudzbina, popust) => api.put(`/porudzbine/${idPorudzbina}`, { popust } ),
  otkazi: (idPorudzbina) => api.post(`/porudzbine/otkazi/${idPorudzbina}`),
};

export const stavkaPorudzbineApi = {
  getByPorudzbina: (idPorudzbina) => api.get(`/stavke-porudzbine/porudzbina/${idPorudzbina}`),
  save: (stavka) => api.post("/stavke-porudzbine", stavka),
  delete: (idStavkaPorudzbine) => api.delete(`/stavke-porudzbine/${idStavkaPorudzbine}`),
};

export const otpremnicaApi = {
  getAll: () => api.get("/otpremnice"),
  getById: (idotpremnica) => api.get(`/otpremnice/${idotpremnica}`),
  generisi: (zahtev) => api.post("/otpremnice/generisi", zahtev),
  otkazi: (idotpremnica) => api.post(`/otpremnice/${idotpremnica}/otkazi`),
};

export const stavkaOtpremniceApi = {
  getByOtpremnica: (idotpremnica) => api.get(`/stavke-otpremnice/otpremnica/${idotpremnica}`),
};

export default api;