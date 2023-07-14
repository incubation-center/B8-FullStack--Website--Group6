import React from "react";
import ButtonWithAvatar from "./ButtonWithAvatar";
import Avatar1 from "../assets/userProfile/Avatar-1.png";

interface Member {
  avatarSrc?: string;
  username: string;
}

interface AdminsSectionProps {
  role: string;
  members: Member[];
  clearIconsStyle: React.CSSProperties;
  onClearClick: () => void;
  onAddClick: () => void;
}

const RoleSection: React.FC<AdminsSectionProps> = ({
  role,
  members,
  clearIconsStyle,
  onClearClick,
  onAddClick,
}) => {
  return (
    <>
      <div className="admins flex flex-row items-center justify-between">
        <span className="mr-2 font-bold">{role}</span>
        <button
          className="bg-sky-500 hover:bg-sky-600 text-white font-semibold py-2 px-5 rounded-full"
          onClick={onAddClick}
        >
          Add
        </button>
      </div>
      <div className="admin-profile flex flex-col gap-y-5 sm:flex-row  overflow-scroll sm:gap-x-3">
        {members.map((member, index) => (
          <ButtonWithAvatar
            key={index}
            avatarSrc={member.avatarSrc || Avatar1}
            name={member.username}
            clearIconsStyle={clearIconsStyle}
            onClearClick={onClearClick}
          />
        ))}
      </div>
    </>
  );
};

export default RoleSection;
