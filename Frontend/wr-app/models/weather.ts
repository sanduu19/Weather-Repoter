export interface Weather {
    location: string;
    condition: string;
    conditionIconURL: string;
    temperatureF: number;
    temperatureC: number;
    windKph: number;
    humidity: number;
    uvIndex: number;
}
