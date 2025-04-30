import { useState } from "react";
import styles from "./Input.module.css";

const Input = ({ titleInput, placeholderInput, widthInput, paddingInput }) => {
  const [text, setText] = useState("");

  return (
    <div className={styles.container}>
      <label>{titleInput}</label>
      <br />
      <input
        type="text"
        value={text}
        placeholder={placeholderInput}
        onChange={(e) => setText(e.target.value)}
        style={{ width: widthInput, padding: paddingInput }}
      />
    </div>
  );
};

export default Input;
