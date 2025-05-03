import { Link, useParams } from "react-router-dom";
import Button from "../components/Button";
import styles from "./SingleRecipePage.module.css";

const SingleRecipePage = () => {
  const param = useParams();
  return (
    <>
      <h1>Recipe of {param.recipeSlug}</h1>
      <div className={styles.backBtn}>
        <Link to=".." relative="path">
          <Button paddingBtn="8px 4%" borderRadiusBtn="50px">
            Back
          </Button>
        </Link>
      </div>
    </>
  );
};

export default SingleRecipePage;
