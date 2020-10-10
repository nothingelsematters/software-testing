import React from "react";
import { render, screen } from "@testing-library/react";
import App from "./App";

describe("Everything is rendered", () => {

  beforeEach(() => {
    render(<App />);
  });

  it("renders navigation", () => {
    const navigation = ["Home", "Login", "About"];

    for (var i = 0; i < navigation.length; i++) {
      const elements = screen.getAllByText(new RegExp(navigation[i], "i"));
      expect(elements.length).toBeGreaterThan(0);
      for (var j = 0; j < elements.length; j++) {
        expect(elements[j]).toBeInTheDocument();
      }
    }
  });

  it("renders home", () => {
    const element = screen.getByText(/Home page/i);
    expect(element).toBeInTheDocument();
  });
})
