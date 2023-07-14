import React from "react";
import { MdClear } from "react-icons/md";

interface ButtonWithAvatarProps {
  avatarSrc: string;
  name: string;
  clearIconsStyle: React.CSSProperties;
  onClearClick: () => void;
}

const ButtonWithAvatar: React.FC<ButtonWithAvatarProps> = ({
  avatarSrc,
  name,
  clearIconsStyle,
  onClearClick,
}) => {
  return (
    <div className="min-w-[163px]  min-h-[40px] max-w-[170px] sm:max-w-[270px] poller-1 flex justify-between items-center border border-sky-500 rounded-full px-1 py-0 ">
      <img src={avatarSrc} alt="Profile" className="w-10 h-10" />
      <span className="text-sky-500">{name.split(" ")[0]}</span>
      <MdClear
        style={clearIconsStyle}
        className="bg-sky-500 rounded-full"
        onClick={onClearClick}
      />
    </div>
  );
};

export default ButtonWithAvatar;
