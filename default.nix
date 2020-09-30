{ pkgs ? import <nixpkgs> { } }:

pkgs.buildFHSUserEnv rec {
  name = "testing-frontend-env";

  targetPkgs = pkgs:
    with pkgs; [
      nodejs-14_x

      # goodies
      git
      zsh
      exa
      fd
      bat
      ripgrep
    ];

  runScript = ''
    sh -c "NIX_NAME=${name} zsh"
  '';
}
