import React, { useState } from "react";
import { useQuery } from "react-query";
import Ellipse1008 from "../../../assets/community/Ellipse1008.png";
import Ellipse10010 from "../../../assets/community/Ellipse10010.png";
import Ellipse10011 from "../../../assets/community/Ellipse10011.png";
import Ellipse1009 from "../../../assets/community/Ellipse1009.png";
import { apiURL, accessToken } from "../../../config/config";

interface Community {
  id: number;
  name: string;
  description: string;
}

interface FavoriteProps {
  searchQuery: string;
}

function UserCommunity({ searchQuery }: FavoriteProps) {
  const [isClicked, setIsClicked] = useState(false);
  const { data, isLoading, isError, error } = useQuery("communityData", () =>
    fetch(`${apiURL}/api/v1/community_members/user`, {
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    })
      .then((response) => response.json())
      .then((data) => data)
  );

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (isError) {
    return <div>Error: {(error as Error)?.message}</div>;
  }

  const favorites = [
    { image: Ellipse1008, name: "Party" },
    { image: Ellipse10010, name: "Game Ball Weekend" },
    { image: Ellipse10011, name: "Tv Phum C Ey" },
    { image: Ellipse1009, name: "Saturday Phirk" },
  ];

  const filteredFavorites = favorites.filter((favorite) =>
    favorite.name.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const handleCommunityClick = (
    e: React.MouseEvent<HTMLDivElement | HTMLButtonElement, MouseEvent>,
    community: Community
  ): void => {
    console.log("Clicked", community.id);
    setIsClicked(true);
  };

  return (
    <div className="profile flex flex-col gap-y-3 pb-4 ml-5 mr-1 overflow-hidden hover:overflow-auto community-scrolling">
      {data?.community.map((community: any, index: any) => {
        return (
          <React.Fragment key={index}>
            <div
              className={`flex cursor-pointer`}
              key={community.id}
              onClick={(e) => handleCommunityClick(e, community)}
            >
              <img
                src={Ellipse1008}
                alt={`Community ${community.id}`}
                className="w-8 h-8 rounded-full mr-2 border-2 border-blue-500"
              />
              <h1>{community.name}</h1>
            </div>
          </React.Fragment>
        );
      })}
    </div>
  );
}

export default UserCommunity;
