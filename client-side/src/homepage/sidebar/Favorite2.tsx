import React from "react";
import Ellipse1006 from "../../assets/community/Ellipse1006.png";
import Ellipse1007 from "../../assets/community/Ellipse1007.png";

function Favorite2() {
  return (
    <div className="profile flex flex-col gap-y-7">
      <div className="flex">
        <img
          src={Ellipse1006}
          alt="Profile 1"
          className="w-8 h-8 rounded-full mr-2 border-2 border-blue-500"
        />
        <h1>KIT</h1>
      </div>
      <div className="flex bg-sky-50 py-2">
        <img
          src={Ellipse1007}
          alt="Profile 1"
          className="w-8 h-8 rounded-full mr-2 border-2 border-blue-500"
        />
        <h1>Moringa</h1>
      </div>
    </div>
  );
}

export default Favorite2;
