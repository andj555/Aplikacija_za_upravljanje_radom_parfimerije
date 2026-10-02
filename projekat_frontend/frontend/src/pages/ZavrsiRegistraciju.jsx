import React, { useState, useEffect } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import { authApi } from '../api';

function ZavrsiRegistraciju() {
    const [searchParams] = useSearchParams();
    const token = searchParams.get('token'); 
    const [ime, setIme] = useState('');
    const [prezime, setPrezime] = useState('');
    const [password, setPassword] = useState('');
    const [greska, setGreska] = useState('');
    const [tokenValidan, setTokenValidan] = useState(false);
    const [ucitavanje, setUcitavanje] = useState(true);
    const navigate = useNavigate();


    console.log("PROVERA :", token);

    useEffect(() => {
        console.log("USAO U USEEFFECT:", token);
        const verifikacija = async () => {
            console.log(" ASYNC");
            if (!token) {
                console.log("NEMA TOKENA");
                setGreska("Nedostaje verifikacioni token");
                setUcitavanje(false);
                return;
            }
        try {
            console.log( token);
                await authApi.verifikacija(token);
                console.log(authApi.verifikacija(token));
                setTokenValidan(true);
            } catch (err) {
                console.log("GRESKA:", err);
                setGreska(err.response?.data?.error || "Token je nevalidan/ istekao");
            } finally {
                console.log("FINALLY");
                setUcitavanje(false);
            }
        };
        verifikacija();
    }, [token]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setGreska("");

        try {
            const response = await authApi.zavrsiRegistraciju({ token, ime, prezime, password });
            alert(response.data.Poruka);
            navigate('/prijava');
        } catch (err) {
            const porukaGreske = err.response?.data?.error || "Greska";
            setGreska(porukaGreske);
        }
    };

    if (ucitavanje) {
        return <p style={{ textAlign: 'center', marginTop: '50px' }}>Validacija tokena</p>;
    }

    if (!tokenValidan) {
        return (
            <div style={{ maxWidth: '400px', margin: '50px auto', textAlign: 'center' }}>
                <h3 style={{ color: 'red' }}>Greška</h3>
                <p>{greska}</p>
            </div>
        );
    }

    return (
        <div style={{ maxWidth: '400px', margin: '50px auto', textAlign: 'left' }}>
            <h2>Zavrsena registracija</h2>
            {greska && <p style={{ color: 'red' }}>{greska}</p>}
            <form onSubmit={handleSubmit}>
                <div style={{ marginBottom: '10px' }}>
                    <label>Ime:</label><br />
                    <input 
                        type="text" 
                        value={ime} 
                        onChange={(e) => setIme(e.target.value)} 
                        required 
                        style={{ width: '100%', padding: '8px' }}
                    />
                </div>
                <div style={{ marginBottom: '10px' }}>
                    <label>Prezime:</label><br />
                    <input 
                        type="text" 
                        value={prezime} 
                        onChange={(e) => setPrezime(e.target.value)} 
                        required 
                        style={{ width: '100%', padding: '8px' }}
                    />
                </div>
                <div style={{ marginBottom: '15px' }}>
                    <label>Lozinka:</label><br />
                    <input 
                        type="password" 
                        value={password} 
                        onChange={(e) => setPassword(e.target.value)} 
                        required 
                        style={{ width: '100%', padding: '8px' }}
                    />
                </div>
                <button type="submit" style={{ padding: '10px 15px', width: '100%' }}>Potvrdi registraciju</button>
            </form>
        </div>
    );
}

export default ZavrsiRegistraciju;