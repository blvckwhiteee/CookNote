import { useState, useEffect, useRef } from "react";
import { Button } from "./Button";
import { TbReload } from "react-icons/tb";
import { IoIosCloseCircleOutline } from "react-icons/io";
import styles from "./CountDownTimer.module.css";

const CountDownTimer = ({ duration }) => {
  const [time, setTime] = useState(duration);
  const [showTimer, setShowTimer] = useState(false);
  const intervalRef = useRef(null);

  const startTimer = () => {
    setShowTimer(true);

    intervalRef.current = setInterval(() => {
      setTime((prev) => {
        if (prev <= 1000) {
          clearInterval(intervalRef.current);
          intervalRef.current = null;
        }
        return prev - 1000;
      });
    }, 1000);
  };

  useEffect(() => {
    clearInterval(intervalRef.current);
    setTime(duration);
    setShowTimer(false);
  }, [duration]);

  const getFormattedTime = (millisecs) => {
    let total_seconds = parseInt(Math.floor(millisecs / 1000));
    let total_minutes = parseInt(Math.floor(total_seconds / 60));
    let total_hours = parseInt(Math.floor(total_minutes / 60));

    let seconds = parseInt(total_seconds % 60);
    let minutes = parseInt(total_minutes % 60);
    let hours = parseInt(total_hours % 24);

    return `${hours.toString().padStart(2, "0")}:${minutes
      .toString()
      .padStart(2, "0")}:${seconds.toString().padStart(2, "0")}`;
  };

  const onClose = () => {
    clearInterval(intervalRef.current);
    intervalRef.current = null;
    setShowTimer(false);
    setTime(duration);
  };

  const onRestart = () => {
    clearInterval(intervalRef.current);
    setTime(duration);
    startTimer();
  };

  return (
    <>
      {!showTimer ? (
        <Button paddingBtn="12px 36px" borderRadiusBtn="50px" onClick={startTimer}>
          Start timer
        </Button>
      ) : (
        <div className={styles.timer}>
          <p>{getFormattedTime(time)}</p>
          <button onClick={onRestart}>
            <TbReload />
          </button>
          <button onClick={onClose}>
            <IoIosCloseCircleOutline />
          </button>
        </div>
      )}
    </>
  );
};

export default CountDownTimer;
