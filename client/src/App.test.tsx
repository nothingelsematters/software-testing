import React from "react";
import { MemoryRouter } from "react-router-dom";
import { render, screen, fireEvent, waitFor, RenderResult } from "@testing-library/react";
import App from "./App";
import Login from "./components/Login";

describe("Everything is rendered", () => {

  beforeEach(() => {
    render(<App />);
  });

  it("renders navigation", () => {
    const navigation = ["Home", "Login", "About"];

    for (let i = 0; i < navigation.length; i++) {
      const elements = screen.getAllByText(new RegExp(navigation[i], "i"));
      expect(elements.length).toBeGreaterThan(0);

      for (let j = 0; j < elements.length; j++) {
        expect(elements[j]).toBeInTheDocument();
      }
    }
  });

  it("renders home", () => {
    const element = screen.getByText(/Home page/i);
    expect(element).toBeInTheDocument();
  });
})

describe("Authorization tests", () => {

  let wrapper: RenderResult;
  let fakeUser = { login: "login", password: "password" };
  let submitLogin: () => void;

  beforeEach(() => {
    submitLogin = jest.fn();
    const props = { submitLogin };
    wrapper = render(
      <MemoryRouter>
        <Login {...props} />
      </MemoryRouter>
    );
  });

  it("Rendered test", () => {
    const loginElements = wrapper.getAllByText(/login/i);
    expect(loginElements.length).toBeGreaterThan(0);

    for (let j = 0; j < loginElements.length; j++) {
      expect(loginElements[j]).toBeInTheDocument();
    }

    const passwordElements = wrapper.getAllByPlaceholderText(/password/i);
    expect(passwordElements.length).toBeGreaterThan(0);

    for (let j = 0; j < passwordElements.length; j++) {
      expect(passwordElements[j]).toBeInTheDocument();
    }
  });

  it("User authorization", async () => {
    let loginNode = wrapper.getByPlaceholderText("login") as HTMLInputElement;
    let passwordNode = wrapper.getByPlaceholderText("password") as HTMLInputElement;
    let loginButtonNode = wrapper.getByText("login") as HTMLInputElement;
    // let formNode = wrapper.baseElement.getElementsByClassName("form")[0] as HTMLFormElement;

    fireEvent.change(loginNode, { target: { value: fakeUser.login } });
    fireEvent.change(passwordNode, { target: { value: fakeUser.password } });

    // formNode.trigger("submit");
    // formNode.submit();
    fireEvent.click(loginButtonNode);

    await waitFor(() => {
      expect(submitLogin).toBeCalledTimes(1);
    });
  });
})
