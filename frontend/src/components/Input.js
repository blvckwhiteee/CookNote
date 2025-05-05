import styles from "./Input.module.css";

const Input = ({ titleInput, placeholderInput, widthInput, paddingInput }) => {
  return (
    <div className={styles.container}>
      {titleInput && (
        <div>
          <label>{titleInput}</label>
          <br />
        </div>
      )}
      <input
        type="text"
        placeholder={placeholderInput}
        style={{ width: widthInput, padding: paddingInput }}
      />
    </div>
  );
};

export default Input;
