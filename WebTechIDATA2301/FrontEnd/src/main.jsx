import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import TopPartOfPage from './topPartOfPage/TopPartOfPage.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
      {/*<App />*/}
    <TopPartOfPage />
  </StrictMode>,
)
