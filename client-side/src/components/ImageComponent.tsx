import React, { useState, useEffect } from "react";

interface ImageComponentProps {
  binaryData: string | ArrayBuffer | null | undefined;
}

const ImageComponent: React.FC<ImageComponentProps> = ({ binaryData }) => {
  const [imageUrl, setImageUrl] = useState<string | undefined>();
  console.log("greate");

  useEffect(() => {
    if (binaryData) {
      // Create a Blob object from the binary data
      const blob = new Blob([binaryData], { type: "image/jpeg" });

      // Create a URL for the Blob object
      const imageUrl = URL.createObjectURL(blob);

      // Set the image URL in the state
      setImageUrl(imageUrl);

      // Clean up the URL when the component unmounts
      return () => URL.revokeObjectURL(imageUrl);
    }
  }, [binaryData]);

  return <img src={imageUrl} alt="Image" />;
};

export default ImageComponent;
