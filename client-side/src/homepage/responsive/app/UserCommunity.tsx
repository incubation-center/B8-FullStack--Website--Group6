import React, { useState, useRef, useEffect } from "react";
import Ellipse1008 from "../../../assets/community/Ellipse1008.png";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../../../redux/store";
import { useNavigate } from "react-router-dom";
import { setInCommunityId } from "../../../redux/slices/Community";

interface Community {
  id: number;
  name: string;
  description: string;
}

function UserCommunity() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [activeCommunity, setActiveCommunity] = useState<number | null>(null);
  const { community } = useSelector((state: RootState) => state.userCommunity);

  const { searchTerm } = useSelector((state: RootState) => state.community);

  const firstButtonRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    // Simulate a click on the first button when the component mounts
    if (firstButtonRef.current) {
      firstButtonRef.current.click();
    }
  }, []);

  const handleCommunityClick = (
    e: React.MouseEvent<HTMLDivElement | HTMLButtonElement, MouseEvent>,
    community: Community
  ): void => {
    e.preventDefault();
    setActiveCommunity(community.id);
    dispatch(setInCommunityId(community.id));
    localStorage.setItem("communityId", `${community.id}`);

    navigate(`/community/${community.id}`);
  };

  return (
    <div className="profile flex flex-col pt-2 gap-y-2  mt-5 mr-1 h-80 overflow-hidden hover:overflow-auto community-scrolling">
      {community
        .filter((community) => {
          return searchTerm.toLocaleLowerCase() === ""
            ? community
            : community.name.toLocaleLowerCase().includes(searchTerm);
        })
        .map((community: any, index: any) => {
          return (
            <React.Fragment key={index}>
              <div
                ref={index === 0 ? firstButtonRef : null}
                className={`relative flex items-center cursor-pointer py-2 px-4 ${
                  activeCommunity === community.id &&
                  "bg-blue-100 transform -skew-x-0"
                }`}
                key={community.id}
                onClick={(e) => handleCommunityClick(e, community)}
              >
                {activeCommunity === community.id && (
                  <div className="absolute w-2 h-full left-0 rounded-tr-lg rounded-br-lg bg-gradient-to-b from-cyan-400 to-blue-500 opacity-70"></div>
                )}
                {/* <img
                src={Ellipse1008}
                alt={`Community ${community.id}`}
                className="w-8 h-8 rounded-full mr-2 border-2 border-blue-500"
              /> */}
                <div className="flex justify-center items-center w-9 h-9 rounded-full mr-2 border border-blue-500">
                  <span className="font-bold text-xl uppercase">
                    {community.name[0]}
                  </span>
                </div>
                <h1>{community.name}</h1>
              </div>
            </React.Fragment>
          );
        })}
    </div>
  );
}

export default UserCommunity;
