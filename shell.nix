{ pkgs ? import <nixpkgs> { } }:

pkgs.mkShell {
  buildInputs = with pkgs; [
    nodejs-14_x
    nodePackages.react-tools
    jdk

    # goodies
    git
    zsh
    exa
    fd
    bat
    ripgrep
  ];
}
