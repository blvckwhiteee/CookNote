import { useState } from "react";
import { TextField, Autocomplete, Box } from "@mui/material";
import { IngrChip } from "../components/Chip";
import styles from "./IngrSearch.module.css";

const IngrSearch = ({ ingredients }) => {
  const [selectedIngr, setSelectedIngr] = useState([]);
  const [inputValue, setInputValue] = useState("");

  const handleChange = (event, value) => {
    setSelectedIngr(value);
    setInputValue("");
  };
  const handleDelete = (id) => {
    setSelectedIngr(selectedIngr.filter((ingr) => ingr.id !== id));
  };
  return (
    <Box className={styles.inputContainer}>
      <Autocomplete
        multiple
        options={ingredients}
        getOptionLabel={(option) => option.name}
        onChange={handleChange}
        onInputChange={(event, value) => setInputValue(value)}
        value={selectedIngr}
        inputValue={inputValue}
        filterSelectedOptions
        renderInput={(params) => (
          <TextField
            {...params}
            label="Add an ingredient"
            variant="outlined"
            fullWidth
            sx={{
              "& .MuiOutlinedInput-root": {
                borderRadius: "14px",

                "& fieldset": {
                  border: "2px solid #68662a",
                },
              },
            }}
          />
        )}
        renderTags={() => null}
      />
      <Box sx={{ margin: "18px 0" }}>
        {selectedIngr.map((ingredient) => (
          <IngrChip
            label={ingredient.name}
            key={ingredient.id}
            onDelete={() => handleDelete(ingredient.id)}
          />
        ))}
      </Box>
    </Box>
  );
};

export default IngrSearch;
