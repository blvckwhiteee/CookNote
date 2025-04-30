import Input from "../components/Input";

const CreateRecipePage = () => {
  return (
    <>
      <h1>Create a recipe</h1>
      <div>
        <Input
          titleInput="Title"
          placeholderInput="Title"
          widthInput="50%"
          paddingInput="8px 10px"
        />
        {/* This input must be chips */}
        <Input
          titleInput="Ingredients"
          placeholderInput="Ingredients"
          widthInput="50%"
          paddingInput="8px 10px"
        />
      </div>
    </>
  );
};

export default CreateRecipePage;
