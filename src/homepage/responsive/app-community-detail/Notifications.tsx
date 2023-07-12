import React from "react";
import { FiSettings } from "react-icons/fi";
import { IoIosNotificationsOutline } from "react-icons/io";
import { MdOutlineGroups } from "react-icons/md";
import { CgToggleOff, CgToggleOn } from "react-icons/cg";
import AddPermission from "../../popup/AddPermission";

const notificationICons = {
  color: "gray",
};

function Notifications() {
  const [isOpen, setIsOpen] = React.useState(false);
  const openModal = () => {
    setIsOpen(true);
  };
  const closeModal = () => {
    setIsOpen(false);
  };

  const [isToggleOpen, setIsToggleOpen] = React.useState(false);
  const handleToggle = () => {
    setIsToggleOpen((prevState) => !prevState);
  };

  return (
    <div className="flex flex-col gap-y-5 mt-4 border-y py-5 border-gray-300">
      <div className="flex justify-start items-center gap-x-5">
        <FiSettings className="w-6 h-6 text-gray-500" />
        <h1>Edit</h1>
      </div>
      <div className="notifications flex justify-between items-center">
        <div className="toggle-btn flex gap-x-4">
          <IoIosNotificationsOutline className="w-7 h-7 text-gray-500" />
          <h1>Notification</h1>
        </div>
        <div
          className="toggle-btn"
          onClick={handleToggle}
          style={notificationICons}
        >
          {isToggleOpen ? (
            <CgToggleOn className="w-6 h-fit text-blue-custom" />
          ) : (
            <CgToggleOff className="w-6 h-fit text-gray-500" />
          )}
        </div>
      </div>
      <div
        className="flex justify-start items-center gap-x-5 cursor-pointer"
        onClick={openModal}
      >
        <MdOutlineGroups className="w-6 h-6 text-gray-500" />
        <h1>Add Permission</h1>
      </div>
      <AddPermission isOpen={isOpen} onClose={closeModal} />
    </div>
  );
}

export default Notifications;
