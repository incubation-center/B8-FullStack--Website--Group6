import React from "react";
import { MdClear } from "react-icons/md";
import { RootState } from "../redux/store";
import { useSelector } from "react-redux";
import api from "../utils/api";

interface Member {
  avatarSrc?: string;
  username: string;
  id: number;
}

interface ButtonWithAvatarProps {
  id: number;
  avatarSrc: string;
  name: string;
  role: string;
  clearIconsStyle: React.CSSProperties;
  onClearClick: () => void;
  setPollers?: React.Dispatch<React.SetStateAction<Member[]>>;
  setAdmins?: React.Dispatch<React.SetStateAction<Member[]>>;
}

const ButtonWithAvatar: React.FC<ButtonWithAvatarProps> = ({
  avatarSrc,
  name,
  clearIconsStyle,
  onClearClick,
  id,
  role,
  setPollers,
  setAdmins,
}) => {
  // Grabbing the community Id
  const { inCommunityId } = useSelector((state: RootState) => state.community);

  const demoteMember = async (id: number) => {
    // Grabbing the access token
    const accessToken = localStorage.getItem("accessToken");
    try {
      const headers = {
        Authorization: `${accessToken}`,
      };

      const body = { id };

      const response = await api.post(
        `/community_members/demote/community/${inCommunityId}`,
        body,
        { headers }
      );

      console.log(response);
      if (response.status === 200) {
        alert("success");
        if (role === "admin") {
          setPollers?.((prev) => [...prev, { id, username: name }]);
          setAdmins?.((prev) => prev?.filter((obj) => obj.id !== id));
        } else {
          setPollers?.((prev) => prev?.filter((obj) => obj.id !== id));
        }

        window.location.reload();
      }
    } catch (error) {
      alert("Please click on the community again!");
      console.error("Error occurred:", error);
    }
  };
  return (
    <div className="min-w-[163px]  min-h-[40px] max-w-[170px] sm:max-w-[270px] poller-1 flex justify-between items-center border border-sky-500 rounded-full p-1 ">
      <img src={avatarSrc} alt="Profile" className="w-10 h-10" />
      <span className="text-sky-500">{name.split(" ")[0]}</span>
      <MdClear
        style={clearIconsStyle}
        className="bg-sky-500 rounded-full"
        onClick={() => demoteMember(id)}
      />
    </div>
  );
};

export default ButtonWithAvatar;
