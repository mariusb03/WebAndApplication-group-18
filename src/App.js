import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { CartProvider } from "./frontend/context/CartContext";
import TopPartOfPage from "./frontend/components/topPartOfPage/TopPartOfPage";
import BottomPart from "./frontend/components/bottomPartOfPage/BottomPart";
import LandingPage from './frontend/pages/LandingPage/LandingPage';
import AllCoursesPage from './frontend/pages/AllCoursesPage/AllCoursesPage';
import AboutUsPage from './frontend/pages/AboutUsPage/AboutUsPage';
import NotFound from './frontend/pages/NotFoundPage/NotFound';
import LoginPage from "./frontend/pages/LoginPage/LoginPage";
import RegisterPage from "./frontend/pages/RegisterPage/RegisterPage";
import CartPage from "./frontend/pages/CartPage/CartPage";
import ProfilePage from "./frontend/pages/ProfilePage/ProfilePage";
import './App.css';

function App() {
    return (
        <CartProvider>
            <div className="App">
                <Router>
                    <TopPartOfPage />
                    <Routes>
                        <Route path="/" element={<LandingPage />} />
                        <Route path="/courses" element={<AllCoursesPage />} />
                        <Route path="/about" element={<AboutUsPage />} />
                        <Route path="/login" element={<LoginPage />} />
                        <Route path="/register" element={<RegisterPage />} />
                        <Route path="/profile" element={<ProfilePage />} />
                        <Route path="/cart" element={<CartPage />} />
                        <Route path="*" element={<NotFound />} />
                    </Routes>

                    <BottomPart />
                </Router>
            </div>
        </CartProvider>
    );
}

export default App;