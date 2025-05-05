import Chip from "@mui/material/Chip";
import { styled } from "@mui/material/styles";

export const IngrChip = styled(Chip)({
  backgroundColor: "#68662a",
  color: "white",
  borderRadius: "2px",
  opacity: "55%",
  fontSize: "16px",
  marginRight: "5px",
  "& .MuiChip-deleteIcon": { color: "white" },
});
