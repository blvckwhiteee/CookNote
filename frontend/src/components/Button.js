import styles from "./Button.module.css";

const Button = ({ children, paddingBtn, borderRadiusBtn, onClick }) => {
  return (
    <button
      onClick={onClick}
      className={styles.button}
      style={{ padding: paddingBtn, borderRadius: borderRadiusBtn }}
    >
      {children}
    </button>
  );
};

export default Button;
