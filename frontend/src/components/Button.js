import styles from "./Button.module.css";

const Button = ({ children, paddingBtn, borderRadiusBtn, onClick }) => {
  return (
    <button
      onClick={onClick}
      className={styles.standartBtn}
      style={{ padding: paddingBtn, borderRadius: borderRadiusBtn }}
    >
      {children}
    </button>
  );
};

const GreenButton = ({ children, paddingBtn, borderRadiusBtn, onClick }) => {
  return (
    <button
      onClick={onClick}
      className={styles.greentBtn}
      style={{ padding: paddingBtn, borderRadius: borderRadiusBtn }}
    >
      {children}
    </button>
  );
};

export { Button, GreenButton };
