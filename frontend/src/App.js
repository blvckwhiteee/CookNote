import { BrowserRouter, Route, Routes } from "react-router-dom";
import Header from "./pages/Header";
import HomePage from "./pages/HomePage";
import CreateRecipePage from "./pages/CreateRecipePage";
import FindRecipePage from "./pages/FindRecipePage";
import NotFound from "./pages/NotFound";
import SingleRecipePage from "./pages/SingleRecipePage";
import StepsPage from "./pages/StepsPage";
import "./App.css";

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route path="/" element={<Header />}>
            <Route index element={<HomePage />} />
            <Route path="create-recipe" element={<CreateRecipePage />} />
            <Route path="find-recipe" element={<FindRecipePage />} />
            <Route
              path="find-recipe/:recipeSlug/:recipeId"
              element={<SingleRecipePage />}
            />
            <Route
              path="find-recipe/:recipeSlug/:recipeId/steps"
              element={<StepsPage />}
            />

            <Route path="*" element={<NotFound />} />
          </Route>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
