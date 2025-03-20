import './boxStyle.css'

function CourseColumBox() {
  return (
    <div className={"columBox"}>
      <div className={"courseRow"} id={"IT"}>
        <div className={"courseBox"}>
          <button className={"pg"} id={"ITButton"}>
            Information Technology
          </button>
        </div>
      </div>
      <div className={"courseRow"} id={"DigitalMarketing"}>
        <div className={"courseBox"}>
          <button className={"pg"} id={"DMButton"}>
            Information Technology
          </button>
        </div>
      </div>
      <div className={"courseRow"} id={"BusinessAndManagement"}>
        <div className={"courseBox"}>
          <button className={"pg"} id={"BMButton"}>
            Information Technology
          </button>
        </div>
      </div>
      <div className={"courseRow"} id={"DataScienceAndAnalytics"}>
        <div className={"courseBox"}>
          <button className={"pg"} id={"DSAButton"}>
            Information Technology
          </button>
        </div>
      </div>
    </div>
  )
}


export default CourseColumBox;