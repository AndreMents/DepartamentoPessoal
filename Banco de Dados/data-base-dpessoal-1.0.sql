-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 20-Ago-2020 às 19:47
-- Versão do servidor: 10.4.14-MariaDB
-- versão do PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `db_dp`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbcontrato`
--

CREATE TABLE `tbcontrato` (
  `codContra` int(10) UNSIGNED NOT NULL,
  `tbfuncionarios_codFuncionario` int(11) NOT NULL,
  `dataAdmiContra` date NOT NULL,
  `responsaContra` varchar(45) DEFAULT NULL,
  `observaContra` varchar(155) DEFAULT NULL,
  `dataVenc30dd` date NOT NULL,
  `dataVenc90dd` date NOT NULL,
  `statusContra` int(11) NOT NULL,
  `statusContra30dd` int(11) NOT NULL,
  `statusContra90dd` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbcoparti`
--

CREATE TABLE `tbcoparti` (
  `codCop` int(11) NOT NULL,
  `tbfuncionarios_codFuncionario` int(11) NOT NULL,
  `geradoPar` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbcotista`
--

CREATE TABLE `tbcotista` (
  `codCota` int(11) NOT NULL,
  `tbfuncionarios_codFuncionario` int(11) NOT NULL,
  `dataAdmi` date DEFAULT NULL,
  `dataVenci` date DEFAULT NULL,
  `observa` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbempresas`
--

CREATE TABLE `tbempresas` (
  `codEmp` int(11) NOT NULL,
  `nomeEmp` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbfuncionarios`
--

CREATE TABLE `tbfuncionarios` (
  `codFuncionario` int(11) NOT NULL,
  `nomeFuncionario` varchar(250) NOT NULL,
  `cpfFuncionario` text NOT NULL,
  `setorFuncionario` varchar(150) NOT NULL,
  `cargoFuncionario` varchar(150) NOT NULL,
  `turnoFuncionario` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbintegra`
--

CREATE TABLE `tbintegra` (
  `codIntegra` int(10) UNSIGNED NOT NULL,
  `dataUltiIntegra` date NOT NULL,
  `dataVencIntegra` date NOT NULL,
  `dataUltiAso` date NOT NULL,
  `dataVencAso` date NOT NULL,
  `tbfuncionarios_codFuncionario` int(11) NOT NULL,
  `tbempresas_codEmp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbparcela`
--

CREATE TABLE `tbparcela` (
  `codParcela` int(10) UNSIGNED NOT NULL,
  `valorParcela` double NOT NULL,
  `dataVencParcela` date NOT NULL,
  `statusParcela` int(1) NOT NULL,
  `tbcoparti_codCop` int(11) NOT NULL,
  `tbcoparti_tbfuncionarios_codFuncionario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbprocedi`
--

CREATE TABLE `tbprocedi` (
  `codPro` int(11) NOT NULL,
  `nomePro` varchar(150) NOT NULL,
  `valorPro` double NOT NULL,
  `parcelaPro` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- --------------------------------------------------------

--
-- Estrutura da tabela `tbrealiza`
--

CREATE TABLE `tbrealiza` (
  `codRel` int(11) NOT NULL,
  `tbprocedi_codPro` int(11) NOT NULL,
  `tbcoparti_codCop` int(11) NOT NULL,
  `tbcoparti_tbfuncionarios_codFuncionario` int(11) NOT NULL,
  `dataPro` date NOT NULL,
  `medicoPro` varchar(155) DEFAULT NULL,
  `localPro` varchar(155) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tbusuarios`
--

CREATE TABLE `tbusuarios` (
  `codUsuario` int(255) NOT NULL,
  `nomeUsuario` varchar(250) NOT NULL,
  `setorUsuario` varchar(150) NOT NULL,
  `cargoUsuario` varchar(150) NOT NULL,
  `loginUsuario` varchar(45) NOT NULL,
  `senhaUsuario` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `tbcontrato`
--
ALTER TABLE `tbcontrato`
  ADD PRIMARY KEY (`codContra`,`tbfuncionarios_codFuncionario`),
  ADD KEY `fk_tbcontrato_tbfuncionarios1_idx` (`tbfuncionarios_codFuncionario`);

--
-- Índices para tabela `tbcoparti`
--
ALTER TABLE `tbcoparti`
  ADD PRIMARY KEY (`codCop`,`tbfuncionarios_codFuncionario`),
  ADD KEY `fk_tbcoparti_tbfuncionarios1` (`tbfuncionarios_codFuncionario`);

--
-- Índices para tabela `tbcotista`
--
ALTER TABLE `tbcotista`
  ADD UNIQUE KEY `fk_tbcotista_tbfuncionarios1_idx` (`tbfuncionarios_codFuncionario`) USING BTREE,
  ADD UNIQUE KEY `codCota` (`codCota`) USING BTREE;

--
-- Índices para tabela `tbempresas`
--
ALTER TABLE `tbempresas`
  ADD PRIMARY KEY (`codEmp`);

--
-- Índices para tabela `tbfuncionarios`
--
ALTER TABLE `tbfuncionarios`
  ADD PRIMARY KEY (`codFuncionario`),
  ADD UNIQUE KEY `cpfFuncionario` (`cpfFuncionario`) USING HASH;

--
-- Índices para tabela `tbintegra`
--
ALTER TABLE `tbintegra`
  ADD PRIMARY KEY (`codIntegra`),
  ADD KEY `fk_tbintegra_tbfuncionarios1_idx` (`tbfuncionarios_codFuncionario`),
  ADD KEY `fk_tbintegra_tbempresas1_idx` (`tbempresas_codEmp`);

--
-- Índices para tabela `tbparcela`
--
ALTER TABLE `tbparcela`
  ADD PRIMARY KEY (`codParcela`),
  ADD KEY `fk_tbparcela_tbcoparti1_idx` (`tbcoparti_codCop`,`tbcoparti_tbfuncionarios_codFuncionario`);

--
-- Índices para tabela `tbprocedi`
--
ALTER TABLE `tbprocedi`
  ADD PRIMARY KEY (`codPro`);

--
-- Índices para tabela `tbrealiza`
--
ALTER TABLE `tbrealiza`
  ADD PRIMARY KEY (`codRel`),
  ADD KEY `fk_tbprocedi_has_tbcoparti_tbcoparti1_idx` (`tbcoparti_codCop`,`tbcoparti_tbfuncionarios_codFuncionario`),
  ADD KEY `fk_tbprocedi_has_tbcoparti_tbprocedi1_idx` (`tbprocedi_codPro`),
  ADD KEY `tbprocedi_codPro` (`tbprocedi_codPro`,`tbcoparti_codCop`) USING BTREE;

--
-- Índices para tabela `tbusuarios`
--
ALTER TABLE `tbusuarios`
  ADD PRIMARY KEY (`codUsuario`),
  ADD UNIQUE KEY `codUsuario_UNIQUE` (`codUsuario`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tbcontrato`
--
ALTER TABLE `tbcontrato`
  MODIFY `codContra` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `tbcoparti`
--
ALTER TABLE `tbcoparti`
  MODIFY `codCop` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `tbfuncionarios`
--
ALTER TABLE `tbfuncionarios`
  MODIFY `codFuncionario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9080;

--
-- AUTO_INCREMENT de tabela `tbintegra`
--
ALTER TABLE `tbintegra`
  MODIFY `codIntegra` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- AUTO_INCREMENT de tabela `tbparcela`
--
ALTER TABLE `tbparcela`
  MODIFY `codParcela` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=107;

--
-- AUTO_INCREMENT de tabela `tbrealiza`
--
ALTER TABLE `tbrealiza`
  MODIFY `codRel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `tbcontrato`
--
ALTER TABLE `tbcontrato`
  ADD CONSTRAINT `fk_tbcontrato_tbfuncionarios1` FOREIGN KEY (`tbfuncionarios_codFuncionario`) REFERENCES `tbfuncionarios` (`codFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `tbcoparti`
--
ALTER TABLE `tbcoparti`
  ADD CONSTRAINT `fk_tbcoparti_tbfuncionarios1` FOREIGN KEY (`tbfuncionarios_codFuncionario`) REFERENCES `tbfuncionarios` (`codFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `tbcotista`
--
ALTER TABLE `tbcotista`
  ADD CONSTRAINT `fk_tbcotista_tbfuncionarios1` FOREIGN KEY (`tbfuncionarios_codFuncionario`) REFERENCES `tbfuncionarios` (`codFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `tbintegra`
--
ALTER TABLE `tbintegra`
  ADD CONSTRAINT `fk_tbintegra_tbempresas1` FOREIGN KEY (`tbempresas_codEmp`) REFERENCES `tbempresas` (`codEmp`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tbintegra_tbfuncionarios1` FOREIGN KEY (`tbfuncionarios_codFuncionario`) REFERENCES `tbfuncionarios` (`codFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `tbparcela`
--
ALTER TABLE `tbparcela`
  ADD CONSTRAINT `fk_tbparcela_tbcoparti1` FOREIGN KEY (`tbcoparti_codCop`,`tbcoparti_tbfuncionarios_codFuncionario`) REFERENCES `tbcoparti` (`codCop`, `tbfuncionarios_codFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `tbrealiza`
--
ALTER TABLE `tbrealiza`
  ADD CONSTRAINT `fk_tbprocedi_has_tbcoparti_tbcoparti1` FOREIGN KEY (`tbcoparti_codCop`,`tbcoparti_tbfuncionarios_codFuncionario`) REFERENCES `tbcoparti` (`codCop`, `tbfuncionarios_codFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_tbprocedi_has_tbcoparti_tbprocedi1` FOREIGN KEY (`tbprocedi_codPro`) REFERENCES `tbprocedi` (`codPro`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
