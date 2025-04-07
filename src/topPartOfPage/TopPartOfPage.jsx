import Login from '../assets/logos/profilePic.svg'
import Cart from '../assets/logos/cartLogo.svg'
import Logo from '../assets/logos/homeLogo.svg'
import './TopPartStyle.css'
import 'react-refresh/runtime';


/**cals all the functions that are needed to create the top part of the page, and returns the complete
 * top part of the function
 *
 * @returns {JSX.Element}
 */
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

/**add the logo on the left side of the top part of the page
 *
 * @returns {JSX.Element}
 */
function logo() {
  return (
    <button className="homeLogoButton">
      <img src={Logo} className="homeLogo" alt="logo"/>
    </button>
  )
}

/**adds the search field, login and cart buttons in the top center of the top part of the page.
 * Above the page selector buttons
 *
 * @returns {JSX.Element}
 */
function searchLoginAndCart() {
  return (
    <div className="searchLoginAndCart">
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

/**adds the page selector buttons in the bottom center of the top part of the page.
 * Bellow the search field, login and cart buttons
 *
 * @returns {JSX.Element}
 */
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