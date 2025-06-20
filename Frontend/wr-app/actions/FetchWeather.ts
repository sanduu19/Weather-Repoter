import axios from 'axios';
import {Weather} from "@/models/weather";

const apiBaseUrl = process.env.NEXT_PUBLIC_API_BASE_URL;

export async function fetchWeather(city: string): Promise<Weather> {
    console.log(apiBaseUrl);
    const response = await axios.get<Weather>(`${apiBaseUrl}/api/weather?city=${city}`);
    return response.data;
}
