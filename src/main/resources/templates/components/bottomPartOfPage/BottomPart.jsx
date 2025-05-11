import Logo from '../assets/logos/homeLogo.svg'
import './BottomPartStyle.css'

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
