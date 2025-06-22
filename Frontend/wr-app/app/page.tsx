'use client'; // Enables client-side rendering in Next.js 13+

import React, { useEffect, useState } from 'react';
import WeatherCard from "@/components/WeatherCard";
import { Weather } from "@/models/weather";
import { fetchWeather } from "@/actions/FetchWeather";
import Loading from "@/components/loading/LoadingPage";

export default function Home() {
  // State to hold weather data
  const [weatherData, setWeatherData] = useState<Weather | null>(null);

  // Loading state to show spinner or loader
  const [loading, setLoading] = useState(false);

  // Error state to show user-friendly error messages
  const [error, setError] = useState('');

  // City input value
  const [city, setCity] = useState('Colombo');

  /**
   * Fetch weather data for the provided city.
   * Updates weatherData, loading, and error states accordingly.
   */
  const handleFetch = async (cityName: string) => {
    setLoading(true);
    setError('');
    try {
      const data = await fetchWeather(cityName);
      setWeatherData(data);
    } catch (err) {
      if (err instanceof Error) {
        setError(err.message); // Show backend-provided or network error
      } else {
        setError('An unexpected error occurred');
      }
    } finally {
      setLoading(false); // Always stop loading once request completes
    }
  };

  /**
   * Runs once on component mount to load weather data for default city.
   */
  useEffect(() => {
    handleFetch(city).then(r => console.log(`Fetched data: ${r}`));
  }, []);

  /**
   * Triggered when the user submits the search form.
   * Prevents page reload and fetches weather for the entered city.
   */
  const handleSearch = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    handleFetch(city).then(r => console.log(`Fetched data: ${r}`));
  };

  return (
      <main
          className="relative min-h-screen bg-cover bg-center bg-no-repeat text-white"
          style={{ backgroundImage: `url('/weather-bg.jpg')` }}
      >
        {/* Dark overlay to make content readable over background image */}
        <div className="absolute inset-0 bg-black/50 z-0" />

        {/* Foreground weather UI content */}
        <div className="relative z-10 flex flex-col items-center justify-center min-h-screen p-6 space-y-8">
          <h1 className="text-4xl font-bold drop-shadow-lg">
            <span className="text-blue-400">Weather</span> Reporter
          </h1>

          {/* Search bar for city name */}
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

          {/* Conditional rendering based on state */}
          {loading ? (
              <Loading /> // Show loader during fetch
          ) : error ? (
              <p className="text-red-400 text-lg">{error}</p> // Show error if any
          ) : weatherData ? (
              <WeatherCard data={weatherData} /> // Show weather card if data is available
          ) : null}
        </div>
      </main>
  );
}