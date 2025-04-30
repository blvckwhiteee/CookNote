import { useParams } from "react-router-dom";

const SingleRecipePage = () => {
  const param = useParams();
  return <h1>Recipe of {param.recipeSlug}</h1>;
};

export default SingleRecipePage;
