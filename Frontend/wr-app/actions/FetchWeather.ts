import axios from 'axios';
import { Weather } from "@/models/weather";

// Base URL for the backend API, loaded from environment variables
const apiBaseUrl = process.env.NEXT_PUBLIC_API_BASE_URL;

// Type definition for the standard response returned by the backend
type ServiceResponse<T> = {
    success: boolean; // Indicates if the request was successful
    message: string;  // Contains error or success message
    data: T;          // Actual response data of generic type T
};

/**
 * Fetches weather data for a given city by calling the backend API.
 *
 * @param city - The name of the city to fetch weather information for
 * @returns A Promise that resolves to a Weather object
 * @throws Error if the request fails or the backend returns an error
 */
export async function fetchWeather(city: string): Promise<Weather> {
    try {
        // Send GET request to the backend weather endpoint
        const response = await axios.get<ServiceResponse<Weather>>(`${apiBaseUrl}/api/weather?city=${city}`);
        const result = response.data;

        // If the response indicates success, return the weather data
        if (result.success) {
            return result.data;
        } else {
            // Otherwise, throw an error with the backend's message
            throw new Error(result.message);
        }
    } catch (error: unknown) {
        // Handle known Axios errors
        if (axios.isAxiosError(error)) {
            // Try to extract the error message from the backend's response
            const apiMessage = (error.response?.data as { message?: string })?.message;
            throw new Error(apiMessage || "Network or server error occurred");
        }

        // Handle all other unexpected errors
        throw new Error("Unexpected error occurred");
    }
}