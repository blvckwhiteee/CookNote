import { useParams } from "react-router-dom";
import { MdArrowForwardIos } from "react-icons/md";
import { GreenButton } from "../components/Button";
import API from "../services/api";

export const StepsPage = () => {
  const { recipeId } = useParams();
  return (
    <div>
      <div>
        CookingPage <GreenButton>Start timer</GreenButton>
      </div>
      <div>Step 1</div>
      <GreenButton paddingBtn="8px 10px" borderRadiusBtn="100%">
        <MdArrowForwardIos />
      </GreenButton>
    </div>
  );
};

export default StepsPage;
