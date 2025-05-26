import { useEffect, useRef, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { Button, GreenButton } from "../components/Button";
import { IngrChip } from "../components/CustomChip";
import styles from "./SingleRecipePage.module.css";
import API from "../services/api";

const SingleRecipePage = () => {
  const [recipeData, setRecipeData] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");
  const { recipeSlug, recipeId } = useParams();
  const loaderTimeoutRef = useRef(null);

  useEffect(() => {
    loaderTimeoutRef.current = setTimeout(() => setIsLoading(true), 2000);
    API.recipe(recipeId)
      .then((json) => setRecipeData(json))
      .catch((err) => setError(err.message))
      .finally(() => {
        clearTimeout(loaderTimeoutRef.current);
        setIsLoading(false);
      });
  }, [recipeId]);

  if (isLoading) {
    return (
      <div className="loader-container">
        <div className="loader"></div>
      </div>
    );
  }

  if (error) {
    return <h1 className="message">Error: {error}</h1>;
  }

  return (
    <>
      {recipeData && (
        <>
          <div className={styles.headerContainer}>
            <div className={styles.imgBoxMain}>
              <img src="/imgs_delete/borsch-36745.jpg" alt={recipeData.title} />
            </div>
            <div className={styles.rightContainer}>
              <div className={styles.header}>
                <h1>{recipeData.title}</h1>
                <Link to="../.." relative="path">
                  <GreenButton paddingBtn="8px 20px" borderRadiusBtn="50px">
                    Back
                  </GreenButton>
                </Link>
              </div>
              <div className={styles.imgBoxSmall}>
                <img src="/imgs_delete/borsch-36745.jpg" alt={recipeData.title} />
              </div>
            </div>
          </div>
          <div className={styles.infoContainer}>
            <p>Ingredients:</p>
            {recipeData.ingredients.map((ingr) => (
              <IngrChip label={[ingr.name, ingr.quantity, ingr.unit].join(" ")} />
            ))}
            <p>
              Time to cook: <span>{recipeData.timeToCook}</span> sec
            </p>
          </div>
        </>
      )}
      <div className={styles.footerBtn}>
        <Link to={`/find-recipe/${recipeSlug}/${recipeId}/steps`}>
          <Button paddingBtn="12px 40px" borderRadiusBtn="50px">
            Start to cook
          </Button>
        </Link>
      </div>
    </>
  );
};

export default SingleRecipePage;
