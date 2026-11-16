import { useEffect, useState } from 'react'

type Health = { status: string }

function App() {
  const [health, setHealth] = useState<Health | null>(null)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    fetch('/actuator/health')
        .then((res) => res.json())
        .then(setHealth)
        .catch((err) => setError(String(err)))
  }, [])

  return (
      <main style={{ fontFamily: 'system-ui', padding: 32 }}>
        <h1>Billing Platform</h1>
        {error && <p>❌ Backend unreachable: {error}</p>}
        {!health && !error && <p>Checking backend…</p>}
        {health && <p>Backend status: <strong>{health.status}</strong></p>}
      </main>
  )
}

export default App