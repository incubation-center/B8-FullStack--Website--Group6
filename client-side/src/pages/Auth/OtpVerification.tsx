import React, { useRef, useEffect } from "react";
import { BsCheckCircle } from "react-icons/bs";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../../redux/store";
import { setOtpCodes, setActiveOtpIndex } from "../../redux/slices/Otp";

let currentOtpIndex = 0;

const OtpVerification = () => {
  const dispatch = useDispatch();
  const { otpCodes, activeOtpIndex } = useSelector(
    (state: RootState) => state.otp
  );

  const inputRef = useRef<HTMLInputElement>(null);

  const handleOptOnChange = (e: React.ChangeEvent<HTMLInputElement>): void => {
    const value = e.target.value;
    const newOtpCodes: string[] = [...otpCodes];
    newOtpCodes[currentOtpIndex] = value.substring(value.length - 1);

    if (!value) dispatch(setActiveOtpIndex(currentOtpIndex - 1));
    else dispatch(setActiveOtpIndex(currentOtpIndex + 1));

    dispatch(setOtpCodes(newOtpCodes));
  };

  const handleOnKeyDown = (
    { key }: React.KeyboardEvent<HTMLInputElement>,
    index: number
  ) => {
    currentOtpIndex = index;
    if (key === "Backspace") dispatch(setActiveOtpIndex(currentOtpIndex - 1));
  };

  useEffect(() => {
    inputRef.current?.focus();
  }, [activeOtpIndex]);

  const handleResend = () => {
    console.log("Resend Code");
  };
  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    console.log("Verifying");
  };

  return (
    <div className="flex justify-center items-center h-screen">
      <form
        onSubmit={handleSubmit}
        className="flex justify-center flex-col h-auto  text-gray-500 px-8 py-6 bg-white md:rounded-md md:shadow-lg lg:shadow-lg"
      >
        <div className="flex items-center gap-2">
          <h1 className="text-blue-custom text-2xl font-bold">Verification</h1>
          <BsCheckCircle className="text-green-600" />
        </div>
        <small>Please enter the verification code we sent to your email.</small>
        <div className="my-4 flex space-x-3 md:justify-around lg:justify-around items-center">
          {otpCodes.map((_, index) => {
            return (
              <React.Fragment key={index}>
                <input
                  ref={index === activeOtpIndex ? inputRef : null}
                  type="number"
                  className="w-full h-14 md:w-14 lg:w-14 border text-gray-500 rounded bg-transparent outline-none text-center font-semibold text-xl spin-button-none border-gray-400 focus:outline-none focus:ring-1 focus:ring-blue-500 transition"
                  onChange={handleOptOnChange}
                  onKeyDown={(e) => handleOnKeyDown(e, index)}
                  value={otpCodes[index]}
                />
              </React.Fragment>
            );
          })}
        </div>
        <div className="flex justify-center">
          <span className="text-center text-sm">
            Not received?{" "}
            <button
              type="button"
              className="text-blue-custom font-bold hover:opacity-70"
              onClick={handleResend}
            >
              Resend
            </button>
          </span>
        </div>
        <button
          id="submit"
          className="w-full text-white uppercase bg-blue-custom rounded mt-4 py-2 hover:opacity-70"
        >
          Verify
        </button>
      </form>
    </div>
  );
};

export default OtpVerification;
