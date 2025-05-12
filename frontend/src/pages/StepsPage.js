import { useParams } from "react-router-dom";
import { MdArrowForwardIos } from "react-icons/md";
import Button from "../components/Button";
import API from "../services/api";

export const StepsPage = () => {
  const { recipeId } = useParams();
  return (
    <div>
      <div>
        CookingPage <Button>Start timer</Button>
      </div>
      <div>Step 1</div>
      <Button paddingBtn="8px 10px" borderRadiusBtn="100%">
        <MdArrowForwardIos />
      </Button>
    </div>
  );
};

export default StepsPage;
