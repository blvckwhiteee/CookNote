import { Link, useParams } from "react-router-dom";
import Button from "../components/Button";
import styles from "./SingleRecipePage.module.css";
import API from "../services/api";

const SingleRecipePage = () => {
  const { recipeSlug, recipeId } = useParams();
  return (
    <>
      <h1>Recipe of {recipeSlug}</h1>
      <div className={styles.Btn}>
        <Link to={`/find-recipe/${recipeSlug}/${recipeId}/steps`}>
          <Button paddingBtn="8px 40px" borderRadiusBtn="50px">
            Start to cook
          </Button>
        </Link>
        <Link to="../.." relative="path">
          <Button paddingBtn="10px 30px" borderRadiusBtn="50px">
            Back
          </Button>
        </Link>
      </div>
    </>
  );
};

export default SingleRecipePage;
