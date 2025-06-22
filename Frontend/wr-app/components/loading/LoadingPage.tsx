/**
 * LoadingPage component displays a simple loading spinner and message.
 * It is used to indicate that data is being fetched or processed.
 */
export default function LoadingPage() {
    return (
        // Card container with blurred glass effect and styling
        <div className="bg-white/20 backdrop-blur-lg shadow-lg rounded-2xl p-6 text-white max-w-sm w-full space-y-4 border border-white/30 text-center">

            {/* Spinner: Animated spinning circle */}
            <div className="animate-spin rounded-full h-12 w-12 border-t-4 border-white border-opacity-70 mx-auto" />

            {/* Loading text */}
            <p className="text-lg font-medium drop-shadow">
                Loading...
            </p>
        </div>
    );
}
