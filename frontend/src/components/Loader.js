import { useEffect, useRef, useState } from "react";
import styles from "./Loader.module.css";

const Loader = ({ isLoading, delay = 1000 }) => {
  const [isShowLoading, setIsShowLoading] = useState(false);
  const timerRef = useRef(null);

  useEffect(() => {
    if (isLoading) {
      timerRef.current = setTimeout(() => setIsShowLoading(true), delay);
    } else {
      clearTimeout(timerRef.current);
      setIsShowLoading(false);
    }
    return () => clearTimeout(timerRef.current);
  }, [isLoading, delay]);

  if (!isLoading || !isShowLoading) return null;

  return (
    <div className={styles.loaderContainer}>
      <div className={styles.loader}></div>
    </div>
  );
};

export default Loader;
