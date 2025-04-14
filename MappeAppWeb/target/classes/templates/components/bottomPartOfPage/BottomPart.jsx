import Logo from '../assets/logos/homeLogo.svg'
import './BottomPartStyle.css'
import 'react-refresh/runtime';

function BottomPart() {
  return (
    <div className="bottomBox">
      <button className="homeLogoButton">
        <img src={Logo} className="homeLogo" alt="logo"/>
      </button>
    </div>
  );
}

export default BottomPart;
