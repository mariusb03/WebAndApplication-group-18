import Login from '../assets/logos/profilePic.svg'
import Cart from '../assets/logos/cartLogo.png'
import Logo from '../assets/logos/homeLogo.svg'
import './TopPartStyle.css'

function topPartOfPage() {
  return (
    <div className={"topPartBox"}>
      {logo ()}
      <div className="searchAndPageSelector">
        {searchLoginAndCart ()}
        {pageSelector ()}
      </div>
    </div>
  )
}

function logo() {
  return (
    <button className="homeLogoButton">
      <img src={Logo} className="homeLogo" alt="logo"/>
    </button>
  )
}

function searchLoginAndCart() {
  return (
    <div className="buttonAndSearchField">
      <div id="SearchField">
        <input type="text" placeholder="Search" />
      </div>

      <div id="LoginAndCartButtons">
        <div className="cartLoginBt">
          <button>
            <img src={Cart} alt="cart" />
          </button>
        </div>

        <div className="cartLoginBt">
          <button>
            <img src={Login} alt="login" />
          </button>
        </div>
      </div>
    </div>
  )
}

function pageSelector() {
  return (
    <div className="pageSelector">
      <button className="pageSelectorButton">
          Information Technologies
      </button>
      <button className="pageSelectorButton">
          Digital Marketing
      </button>
      <button className="pageSelectorButton">
          Business and Entrepreneurship
      </button>
      <button className="pageSelectorButton">
          Data Science and Analytics
      </button>
    </div>
  )
}

export default topPartOfPage