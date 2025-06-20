'use client';

import { useEffect, useState } from 'react';
import WeatherCard from "@/components/WeatherCard";
import { Weather } from "@/models/weather";
import { fetchWeather } from "@/actions/FetchWeather";
import Loading from "@/components/loading/LoadingPage";

export default function Home() {
  const [weatherData, setWeatherData] = useState<Weather | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [city, setCity] = useState('Colombo');

  const handleFetch = async (cityName: string) => {
    setLoading(true);
    setError('');
    try {
      const data = await fetchWeather(cityName);
      setWeatherData(data);
    } catch (err) {
      if (err instanceof Error) {
        setError(err.message);
      } else {
        setError('An unexpected error occurred');
      }
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    handleFetch(city);
  }, []);

  const handleSearch = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    handleFetch(city);
  };

  return (
      <main
          className="relative min-h-screen bg-cover bg-center bg-no-repeat text-white"
          style={{ backgroundImage: `url('/weather-bg.jpg')` }}
      >
        {/* Dark overlay */}
        <div className="absolute inset-0 bg-black/50 z-0" />

        {/* Foreground content */}
        <div className="relative z-10 flex flex-col items-center justify-center min-h-screen p-6 space-y-8">
          <h1 className="text-4xl font-bold drop-shadow-lg">
            <span className="text-blue-400">Weather</span> Reporter
          </h1>

          <form
              onSubmit={handleSearch}
              className="flex gap-3 bg-white/20 backdrop-blur-md rounded-full px-4 py-2 shadow-md"
          >
            <input
                type="text"
                value={city}
                onChange={(e) => setCity(e.target.value)}
                placeholder="Enter city"
                className="bg-transparent text-white placeholder-gray-200 outline-none w-60"
            />
            <button
                type="submit"
                className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-1 rounded-full font-medium transition"
            >
              Search
            </button>
          </form>

          {loading ? (
              <Loading />
          ) : error ? (
              <p className="text-red-400 text-lg">{error}</p>
          ) : weatherData ? (
              <WeatherCard data={weatherData} />
          ) : null}
        </div>
      </main>
  );
}
