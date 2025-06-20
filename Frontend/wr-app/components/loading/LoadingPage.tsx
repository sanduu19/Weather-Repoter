export default function LoadingPage() {
    return (
        <div className="bg-white/20 backdrop-blur-lg shadow-lg rounded-2xl p-6 text-white max-w-sm w-full space-y-4 border border-white/30 text-center">
            <div className="animate-spin rounded-full h-12 w-12 border-t-4 border-white border-opacity-70 mx-auto" />
            <p className="text-lg font-medium drop-shadow">Loading...</p>
        </div>
    );
}
