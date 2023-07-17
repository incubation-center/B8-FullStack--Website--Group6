import React, { useState, useRef, useEffect } from "react";
import PolliFy from "../../../assets/PolliFy.png";
import Avatar from "../../../assets/Avatar.png";
import { MdTranslate } from "react-icons/md";
import { IoMdNotificationsOutline, IoIosArrowBack } from "react-icons/io";
import QrCode from "../../../assets/icons/qr-code.svg";
import NotificationIcon from "../../../assets/icons/notification.svg";
import Ellipse1007 from "../../../assets/community/Ellipse1007.png";
import { BsQrCode } from "react-icons/bs";
import CommunitySetting from "./CommunitySetting";
import CommunityMembers from "./CommunityMembers";
import PopupModal from "../../popup/PopupModal";
import QRCode from "qrcode.react";
import api from "../../../utils/api";
import { apiURL } from "../../../config/config";
import { useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../../../redux/store";
import { setIsCommunityProfileOpen } from "../../../redux/slices/Community";

function CommunityProfile() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = React.useState(false);
  const [hasAccess, setHasAccess] = useState(false);
  const { isCommunityProfileOpen } = useSelector(
    (state: RootState) => state.community
  );
  const [inviteUrl, setInviteUrl] = useState("");
  const qrCodeRef = useRef(null);
  const { id } = useSelector((state: RootState) => state.userCommunity);
  const { communityMembers } = useSelector(
    (state: RootState) => state.community
  );
  const currentProfile = communityMembers.find((member) => member.id === id);

  useEffect(() => {
    if (currentProfile?.role === "admin" || currentProfile?.role === "owner") {
      setHasAccess(true);
    } else {
      setHasAccess(false);
    }
  }, [hasAccess, communityMembers]);

  const openModal = () => {
    setIsOpen(true);
  };
  const closeModal = () => {
    setIsOpen(false);
  };

  const handleGenerateLink = async () => {
    const communityUUID = "8ffb1ccf-e661-4559-9cc8-da6c0d089b34";
    const invitationLink = `localhost:3000/community_members/join/community/${communityUUID}`;

    await navigator.clipboard.writeText(invitationLink);
    setInviteUrl(invitationLink);
    console.log("Link copied to clipboard:", invitationLink);
  };

  const handleBackToPoll = () => {
    dispatch(setIsCommunityProfileOpen(false));
  };

  return (
    <div
      className={` ${
        isCommunityProfileOpen ? "w-full" : "w-0"
      } absolute z-10 duration-300 right-0 lg:relative font-sans bg-white lg:w-2/6 h-full lg:flex lg:flex-col overflow-hidden`}
    >
      <div className="logo-profile-createPoll flex justify-between lg:justify-end items-center mt-5 ml-5 mr-5">
        <div
          className="flex items-center gap-x-2 lg:hidden cursor-pointer"
          onClick={handleBackToPoll}
        >
          <IoIosArrowBack className="w-6 h-6 text-blue-custom" />
          <span className="text-lg">Polls</span>
        </div>
        <div className="translate flex gap-x-3 items-center lg:justify-end">
          <MdTranslate className="w-6 h-6" />
          <div className="pr-2">
            <img
              className="w-6 h-6 text-gray-500"
              src={NotificationIcon}
              alt="Notification Icon"
            />
          </div>
          <h1 className="lg:text-[17px] lg:font-sans lg:font-bold">TED</h1>
          <div className="relative">
            <img
              src={Avatar}
              alt='Profile 1'
              className='w-10 h-10 rounded-full mr-2 border-2 border-blue-500'
            />
            <span className='bottom-1 left-8 absolute w-3 h-3 bg-green-400 border-2 border-white dark:border-gray-800 rounded-full'></span>
          </div>
        </div>
      </div>
      <div className="border border-gray-200 mt-8 lg:hidden"></div>
      <div className="Moringa flex flex-col gap-y-3 mt-12 justify-center items-center">
        <img
          src={Ellipse1007}
          alt='moringa'
          className='w-16 h-16 rounded-full mr-2 border border-blue-custom cursor-pointer'
          onClick={openModal}
        />
        <h1 className='text-[15px]'>Moringa</h1>
        <div className='flex justify-center items-center gap-x-5'>
          <QRCode
            className='w-64 h-64'
            value={inviteUrl}
            renderAs='svg'
            ref={qrCodeRef}
          />
          <button
            id="copyButton"
            type="button"
            onClick={() => console.log("Hello")}
            className="bg-blue-custom hover:opacity-70 text-white font-bold py-2 px-5 rounded-lg"
          >
            Copy Link
          </button>
        </div>
      </div>
      {/* <PopupModal isOpen={isOpen} onClose={closeModal} /> */}
      <div className="px-4 mb-3">{hasAccess && <CommunitySetting />}</div>
      <span className="text-black pl-2 pt-4">Current Members</span>
      <div className="mr-1  overflow-hidden hover:overflow-auto community-scrolling">
        <CommunityMembers />
      </div>
    </div>
  );
}

export default CommunityProfile;
