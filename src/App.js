import logo from './logo.svg';
import './App.css';
import 'react-refresh/runtime';
import TopPartOfPage from "./components/topPartOfPage/TopPartOfPage";
import BottomPart from "./components/bottomPartOfPage/BottomPart";
import HeroCarousel from './components/heroCarousel/HeroCarousel';
import LandingPage from './pages/LandingPage';
import AllCoursesPage from './pages/AllCoursesPage';
import AboutUsPage from './pages/AboutUsPage';
import NotFound from './pages/NotFound';

function App() {
    return (
        <div className="App">
            <TopPartOfPage />
            <LandingPage />
            <AllCoursesPage />
            <AboutUsPage />
            <NotFound />
            <BottomPart />
        </div>
    );
}




function App2() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
