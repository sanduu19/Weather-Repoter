import { Weather } from "@/models/weather";

// Props definition for WeatherCard component
interface WeatherCardProps {
    data: Weather; // Weather data passed from parent component
}

/**
 * WeatherCard component displays the weather information
 * including location, condition, temperature, humidity, wind speed, and UV index.
 */
export default function WeatherCard({ data }: WeatherCardProps) {
    return (
        <div className="bg-white/20 backdrop-blur-lg shadow-2xl rounded-2xl p-6 text-white max-w-sm w-full space-y-6 border border-white/30">
            {/* Location Title */}
            <h2 className="text-3xl font-semibold text-center tracking-wide drop-shadow-md">
                {data.location}
            </h2>

            {/* Weather Condition with Icon */}
            <div className="flex items-center justify-center gap-4">
                <img
                    src={`https:${data.conditionIconURL}`} // Weather condition icon URL
                    alt={data.condition} // Alt text for accessibility
                    className="w-16 h-16 drop-shadow-md"
                />
                <p className="text-xl font-light tracking-wide capitalize drop-shadow-sm">
                    {data.condition} {/* Weather condition text */}
                </p>
            </div>

            {/* Weather Details Grid */}
            <div className="grid grid-cols-2 gap-y-4 text-sm">
                {/* Temperature */}
                <div className="space-y-1 text-center">
                    <p className="text-white/80 font-medium drop-shadow-sm">Temperature</p>
                    <p className="text-base font-semibold">{data.temperatureC}°C</p>
                </div>

                {/* Humidity */}
                <div className="space-y-1 text-center">
                    <p className="text-white/80 font-medium drop-shadow-sm">Humidity</p>
                    <p className="text-base font-semibold">{data.humidity}%</p>
                </div>

                {/* Wind Speed */}
                <div className="space-y-1 text-center">
                    <p className="text-white/80 font-medium drop-shadow-sm">Wind</p>
                    <p className="text-base font-semibold">{data.windKph} kph</p>
                </div>

                {/* UV Index */}
                <div className="space-y-1 text-center">
                    <p className="text-white/80 font-medium drop-shadow-sm">UV Index</p>
                    <p className="text-base font-semibold">{data.uvIndex}</p>
                </div>
            </div>
        </div>
    );
}
